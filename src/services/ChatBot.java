/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author 21656
 */
public class ChatBot {

    String[][] chat = {
        //        Message Bienvenu
        {"ahla", "3aslema", "aaslema", "salut", "hi", "hey", "fraise", "whayed"},
        {"Ahla bik Ena Walid", "Ahla cava ?", "Ya man"},
        //        Questions
        {"cava?", "labess?", "how r u?", "finek"},
        {"Walahy Hani mak ta3ref , Khedma (mine w fazet)", "Mak ta3ref el developer ta3abni", "Yooo"},
        //Yes
        {"yes"},
        {"no", "Sayeb 3lina", "Ti leeee 3aad"},
        //        Default
        {"Yeeezi bla blada"}
    };

    public ChatBot() {

    }

    public String[][] getChat() {
        return this.chat;
    }

    public boolean in(String in, String[] str) {

        boolean verif = false;
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals(in)) {
                verif = true;
            }
        }
        return verif;
    }
}
