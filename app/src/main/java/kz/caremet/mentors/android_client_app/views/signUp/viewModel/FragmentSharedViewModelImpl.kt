package kz.caremet.mentors.android_client_app.views.signUp.viewModel

import kz.caremet.mentors.android_client_app.core.data.DataEntities

class FragmentSharedViewModelImpl(): FragmentSharedViewModel{
    var generalInfo: DataEntities.GeneralInfo? = null
    var aboutInfo: DataEntities.AboutInfo? = null

    override fun setAboutInfoData(aboutInfo: DataEntities.AboutInfo) {
        this.aboutInfo = aboutInfo
    }

    override fun setGeneralInfoData(generalInfo: DataEntities.GeneralInfo) {
        this.generalInfo = generalInfo
    }

    override fun getQuestionaryData(): DataEntities.QuestionaryData? {

        if(generalInfo!=null && aboutInfo != null){
            return DataEntities.QuestionaryData.getQuestionaryDataFromInfoClasses(generalInfo!!,aboutInfo!!)
        }

        return null
    }

    override fun getGeneralInfoData(): DataEntities.GeneralInfo?{
        return generalInfo
    }

    override fun getAboutInfoData(): DataEntities.AboutInfo?{
        return aboutInfo
    }
}