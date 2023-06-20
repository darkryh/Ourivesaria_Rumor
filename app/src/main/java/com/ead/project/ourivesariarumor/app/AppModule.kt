package com.ead.project.ourivesariarumor.app

import android.content.Context
import androidx.room.Room
import com.ead.project.ourivesariarumor.data.database.CartDao
import com.ead.project.ourivesariarumor.data.database.OrderDao
import com.ead.project.ourivesariarumor.data.database.ProductDao
import com.ead.project.ourivesariarumor.data.database.RumorDatabase
import com.ead.project.ourivesariarumor.data.repository.AppRepository
import com.ead.project.ourivesariarumor.data.repository.AppRepositoryImpl
import com.ead.project.ourivesariarumor.data.repository.FirebaseClient
import com.ead.project.ourivesariarumor.data.service.AuthenticationService
import com.ead.project.ourivesariarumor.data.service.ProductService
import com.ead.project.ourivesariarumor.data.service.UserService
import com.ead.project.ourivesariarumor.domain.use_case.AuthenticationUseCase
import com.ead.project.ourivesariarumor.domain.use_case.InventoryUseCase
import com.ead.project.ourivesariarumor.domain.use_case.authentication.VerifyEmail
import com.ead.project.ourivesariarumor.domain.use_case.authentication.login.Login
import com.ead.project.ourivesariarumor.domain.use_case.authentication.register.IsInAdultAge
import com.ead.project.ourivesariarumor.domain.use_case.authentication.verification.CheckEmailVerificationUsed
import com.ead.project.ourivesariarumor.domain.use_case.authentication.register.Register
import com.ead.project.ourivesariarumor.domain.use_case.authentication.verification.SendVerification
import com.ead.project.ourivesariarumor.domain.use_case.authentication.verification.TimerVerification
import com.ead.project.ourivesariarumor.domain.use_case.authentication.verification.UpdateCheckEmailVerificationUsed
import com.ead.project.ourivesariarumor.domain.use_case.authentication.verification.UpdateVerificationTime
import com.ead.project.ourivesariarumor.domain.use_case.authentication.verification.VerifyAccount
import com.ead.project.ourivesariarumor.domain.use_case.inventory.AddProduct
import com.ead.project.ourivesariarumor.domain.use_case.inventory.GetProducts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomInstance(
        context: Context
    ) : RumorDatabase = Room.databaseBuilder(
        context,
        RumorDatabase::class.java,
        RumorDatabase.DATABASE
    ).build()

    @Provides
    @Singleton
    fun provideAppRepository(
        productDao: ProductDao,
        orderDao: OrderDao,
        cartDao: CartDao
    ) : AppRepository
    = AppRepositoryImpl(
        productDao = productDao,
        orderDao = orderDao,
        cartDao = cartDao
    )

    @Provides
    @Singleton
    fun provideProductDao(
        database: RumorDatabase
    ) : ProductDao
    = database.productDao()

    @Provides
    @Singleton
    fun provideOrderDao(
        database: RumorDatabase
    ) : OrderDao
    = database.orderDao()

    @Provides
    @Singleton
    fun provideCartDao(
        database: RumorDatabase
    ) : CartDao
    = database.cartDao()

    @Provides
    @Singleton
    fun provideAuthenticationService(firebase: FirebaseClient) : AuthenticationService
    = AuthenticationService(firebase = firebase)

    @Provides
    @Singleton
    fun provideUserService(firebase: FirebaseClient) : UserService
    = UserService(
        firebase = firebase
    )

    @Provides
    @Singleton
    fun provideProductService(@ApplicationContext context: Context,firebase: FirebaseClient) : ProductService
    = ProductService(context, firebase)

    @Provides
    @Singleton
    fun provideAuthenticationUseCase(
        @ApplicationContext context: Context,
        authenticationService: AuthenticationService,
        userService: UserService
    ) : AuthenticationUseCase {
        val checkEmailVerificationUsed = CheckEmailVerificationUsed(context = context)
        val updateCheckEmailVerificationUsed = UpdateCheckEmailVerificationUsed(context = context)
        return AuthenticationUseCase(
            login = Login(
                authenticationService = authenticationService
            )
            ,
            register = Register(
                authenticationService = authenticationService,
                userService = userService
            ),
            sendVerification = SendVerification(
                authenticationService = authenticationService,
                checkEmailVerificationUsed = checkEmailVerificationUsed,
                updateCheckEmailVerificationUsed = updateCheckEmailVerificationUsed,
                updateVerificationTime = UpdateVerificationTime(
                    context = context
                )
            ),
            verifyAccount = VerifyAccount(
                authenticationService = authenticationService
            ),
            verifyEmail = VerifyEmail(
                authenticationService = authenticationService
            ),
            isInAdultAge = IsInAdultAge(),
            checkEmailVerificationUsed = checkEmailVerificationUsed,
            updateCheckEmailVerificationUsed = updateCheckEmailVerificationUsed,
            timerVerification = TimerVerification(
                context = context
            )
        )
    }

    @Provides
    @Singleton
    fun provideInventoryUseCase(productService: ProductService) : InventoryUseCase {
        return InventoryUseCase(
            addProduct = AddProduct(
                productService = productService
            ),
            getProducts = GetProducts(
                productService = productService
            )
        )
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context : Context) : Context = context

    @Provides
    @Singleton
    fun provideFirebaseClient() : FirebaseClient = FirebaseClient()

}