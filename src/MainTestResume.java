import ru.model.Contacts;
import ru.model.Resume;
import ru.model.SectionType;
import ru.model.TextSection;

import java.util.List;

public class MainTestResume {
    public static void main(String[] args) {
        Resume res =new Resume("Unknown");

        res.setContact("Мобилка",Contacts.TELEPHONE);
        res.setContact("Скуре",Contacts.SKYPE);
        res.setContact("Мыло",Contacts.MAIL);
        res.setContact("Линкин", Contacts.LINKEDIN);
        res.setContact("Хаб",Contacts.GITHUB);
        res.setContact("Стак",Contacts.STATCKOVERFLOW);
        res.setContact("Домшняя страница ",Contacts.HOMEPAGE);

        System.out.println(res.getContact(Contacts.TELEPHONE));
        System.out.println(res.getContact(Contacts.SKYPE));
        System.out.println(res.getContact(Contacts.MAIL));
        System.out.println(res.getContact(Contacts.LINKEDIN));
        System.out.println(res.getContact(Contacts.GITHUB));
        System.out.println(res.getContact(Contacts.STATCKOVERFLOW));
        System.out.println(res.getContact(Contacts.HOMEPAGE));
        System.out.println(res.getContact(Contacts.GITHUB));


        res.setSectionType(new TextSection("Неуч"),SectionType.EDUCATION);


        System.out.println(res.getSectionTypeData(SectionType.EDUCATION));

    }
    /*
     TELEPHONE("Телефон"),
    SKYPE("Skype"),
    MAIL("Почта"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STATCKOVERFLOW("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");
     */

}
