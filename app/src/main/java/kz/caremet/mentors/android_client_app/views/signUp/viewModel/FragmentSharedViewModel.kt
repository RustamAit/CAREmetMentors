package kz.caremet.mentors.android_client_app.views.signUp.viewModel

import kz.caremet.mentors.android_client_app.core.DataEntities

interface FragmentSharedViewModel {
        fun setGeneralInfoData(generalInfo: DataEntities.GeneralInfo)
        fun setAboutInfoData(aboutInfo: DataEntities.AboutInfo)
        fun getQuestionaryData(): DataEntities.QuestionaryData?
        fun getGeneralInfoData(): DataEntities.GeneralInfo?
        fun getAboutInfoData(): DataEntities.AboutInfo?
}