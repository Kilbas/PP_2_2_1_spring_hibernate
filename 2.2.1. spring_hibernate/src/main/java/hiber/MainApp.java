package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      User user5 = new User("User5", "NoCar", "user5@mail.ru");
      User user6 = new User("User6", "Lastname6", "user6@mail.ru");

      Car car1 = new Car("Zil",104);
      Car car2 = new Car("MAZ",6305);
      Car car3 = new Car("UAZ",104);
      Car car4 = new Car("Zil",104);
      Car car5 = new Car("Zil",104);
      Car car6 = new Car("IJ", 106);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));
      userService.add(user5);
      userService.add(car5);



      List<User> users = userService.listUsers();
      System.out.println();
      for (User user : users) {
         System.out.println(user);
      }
      System.out.println();
      List<Car> cars = userService.listCars();
      System.out.println();
      for (Car car : cars) {
         System.out.println(car);
      }


      try {
         User userWithCar = userService.getUserByCar("ZIL", 104);
         System.out.println();
         System.out.println(userWithCar);
      } catch (NoResultException e) {
         System.out.println();
         System.out.println("There is no user with such car");
      } finally {
         System.out.println();
      }

      context.close();
   }
}
