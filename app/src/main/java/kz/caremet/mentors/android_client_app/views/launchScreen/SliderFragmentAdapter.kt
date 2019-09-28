package kz.caremet.mentors.android_client_app.views.launchScreen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SliderPageAdapter(fm: FragmentManager, val pageCount: Int): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> SliderFragment.newInstance("Профессиональные психологи",
                "Проект сопровождают профессиональные психологи всегда готовые помочь и дать рекомендации",
                SliderFragmentType.QUESTION
            )
            1 -> SliderFragment.newInstance("Личный кабинет",
                "Каждый Наставник имеет" +
                        "доступ к личному кабинету," +
                        "где может составлять удобный" +
                        "график встреч с ребенком," +
                        "получать быстрые советы от" +
                        "психолога, быть в курсе всех" +
                        "событий в рамках проекта.",
                SliderFragmentType.CONTROL
            )
            2 -> SliderFragment.newInstance("Библиотека",
                "На сайте хранится вся" +
                        "необходимая информация об" +
                        "индивидуальных особенностях" +
                        "детей, ссылки на полезные" +
                        "статьи и различная интересная" +
                        "информация о Наставничестве.",
                SliderFragmentType.AVAILAABILITY)

            3 -> SliderFragment.newInstance("Сообщество",
                "Попробуй наше приложения для ознакомление с ним",
                SliderFragmentType.ACCEPTANCE)

            else -> SliderFragment.newInstance("Все доступно",
                "В рамках проекта Наставничество" +
                        "проходят тематические встречи" +
                        "Наставников, детей и кураторов" +
                        "проекта. Участвуя в проекте, вы" +
                        "попадаете в сообщество" +
                        "неравнодушных людей," +
                        "тех, кому #невсеравно.",
                SliderFragmentType.AVAILAABILITY
            )
        }
    }

    override fun getCount(): Int {
        return pageCount
    }
}