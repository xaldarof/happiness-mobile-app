package pdf.reader.happiness.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.koin.dsl.module
import pdf.reader.happiness.data.cloud.CloudDataRepository
import pdf.reader.happiness.data.cloud.InfoCloudDataSource

val cloudModule = module {
    single<InfoCloudDataSource>{ InfoCloudDataSource.Base(get()) }
    single<CloudDataRepository>{ CloudDataRepository.Base(get()) }
    single<DatabaseReference>{ FirebaseDatabase.getInstance().getReference("data") }
}