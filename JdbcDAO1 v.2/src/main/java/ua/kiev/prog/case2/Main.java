package ua.kiev.prog.case2;

import ua.kiev.prog.shared.Client;
import ua.kiev.prog.shared.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection())
        {
            // remove this
            try {
                try (Statement st = conn.createStatement()) {
                    st.execute("DROP TABLE IF EXISTS Clients");
                    //st.execute("CREATE TABLE Clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) NOT NULL, age INT)");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            ClientDAOImpl2 dao = new ClientDAOImpl2(conn, "Clients");
            dao.createTable(Client.class);

            Client c = new Client("test", 1);
            dao.add(c);
            Client c2 = new Client("test2", 20);
            dao.add(c2);
            System.out.println("ID of client " + c.getName() + " is " + c.getId());
            System.out.println("ID of client " + c2.getName() + " is " + c2.getId());

            List<Client> list = dao.getAll(Client.class, "name");
            for (Client cli : list)
                System.out.println(cli);

            List<Client> list3 = dao.getAll(Client.class, "name", "age");
            for (Client cli : list3)
                System.out.println(cli);

//            List<Client> list = dao.getAll(Client.class);
//            for (Client cli : list)
//                System.out.println(cli);
//
//            list.get(0).setAge(55);
//            dao.update(list.get(0));
//            System.out.println(list.get(0));
            /*

            List<Client> list = dao.getAll(Client.class, "name", "age");
            List<Client> list = dao.getAll(Client.class, "age");
            for (Client cli : list)
                System.out.println(cli);

             */

//            dao.delete(list.get(0));
        }
    }
}
