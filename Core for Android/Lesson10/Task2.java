package Lesson10;

/**
 * * 2. Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров.
 *  * В этот телефонный справочник с помощью метода add() можно добавлять записи,
 *  * а с помощью метода get() искать номер телефона по фамилии.
 *  * Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
 *  * тогда при запросе такой фамилии должны выводиться все телефоны.
 *  * (Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес),
 *  * взаимодействие с пользователем через консоль и т.д). Консоль использовать только для вывода
 *  * результатов проверки телефонного справочника.)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Task2 {
    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();

        System.out.println("Добавляем номера телефонов в справочник:\n");
        phonebook.add("Петров", "22555874");
        phonebook.add("Иванов", "225555874");
        phonebook.add("Сидоров", "22555774");
        phonebook.add("Мартыненко", "22355874");
        phonebook.add("Тимошенко", "72555874");
        phonebook.add("Петров", "29555874");
        phonebook.add("Сидоров", "22555374");
        phonebook.add("Петров", "12555874");


        System.out.println("\n *** \nПоиск номера телефона по фамилии:");
        phonebook.find("Петров");
        phonebook.find("Иванова");

    }
}

class Phonebook {
    private HashMap <String, List<String>> lst;

    public Phonebook() {
        this.lst = new HashMap<>();
    }

    public void add (String name, String phone) {
        if (lst.containsKey(name)) {
            List<String> numbers = lst.get(name);
            if (!numbers.contains(phone)) {
                numbers.add(phone);
                System.out.printf("Для фамилии %s записан номер телефона %s\n", name, phone);
            } else {
                numbers.add(phone);
                System.out.printf("Для фамилии %s добавлен еще один номер телефона %s\n", name, phone);
            }
        } else {
            lst.put(name, new ArrayList<>(Arrays.asList(phone)));
            System.out.printf("Для фамилии %s записан номер телефона %s\n", name, phone);
        }
    }

       public List<String> find (String name){
            if (lst.containsKey(name)) {
                System.out.printf(String.format("Для фамилии %s в справочнике найден телефон %s\n", name, lst.get(name) ));
                return  lst.get(name);
            } else{
                System.out.printf(String.format("Для фамилии %s нет записи в справочнике\n", name ));
                return new ArrayList<>();
            }
        }
}

