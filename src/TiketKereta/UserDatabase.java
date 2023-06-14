package TiketKereta;

import java.util.ArrayList;
import java.util.Scanner;

public class UserDatabase {
    private ArrayList<ArrayList<String>> user = new ArrayList<>();

    // Menambahkan data pengguna ke dalam basis data
    public void addUser(String name, String username, String password, String saldo) {
        ArrayList<String> userData = new ArrayList<>();
        userData.add(name);
        userData.add(username);
        userData.add(password);
        userData.add(saldo);
        user.add(userData);
    }

    // Menghapus data pengguna dari basis data berdasarkan indeks
    public void removeUser(int index) {
        if (index >= 0 && index < user.size()) {
            user.remove(index);
        } else {
            System.out.println("Indeks tidak valid!");
        }
    }

    // Mendapatkan data pengguna dari basis data berdasarkan indeks
    public ArrayList<String> getUser(int index) {
        if (index >= 0 && index < user.size()) {
            return user.get(index);
        } else {
            System.out.println("Indeks tidak valid!");
            return null;
        }
    }

    // Mendapatkan semua data pengguna dari basis data
    public ArrayList<ArrayList<String>> getAllUsers() {
        return user;
    }

    // Mendapatkan jumlah pengguna dalam basis data
    public int getUserCount() {
        return user.size();
    }

}
