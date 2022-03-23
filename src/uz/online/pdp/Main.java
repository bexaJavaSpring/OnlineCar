package uz.online.pdp;

import uz.online.pdp.model.App;
import uz.online.pdp.model.Car;
import uz.online.pdp.model.OilMark;
import uz.online.pdp.model.PaymentType;
import uz.online.pdp.service.AdminService;
import uz.online.pdp.service.UserService;

import java.util.Scanner;

public class Main {
    public static final Scanner SCANNER_NUM = new Scanner(System.in);
    public static final Scanner SCANNER_STR = new Scanner(System.in);
    public static final Scanner SCANNER_DOUBLE = new Scanner(System.in);

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        App app = new App();

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Log out");
            System.out.println("0. Stop");

            System.out.print("Input option: ");
            int option = SCANNER_NUM.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Input email: ");
                    String email = SCANNER_STR.nextLine();

                    System.out.print("Input password: ");
                    String password = SCANNER_STR.nextLine();

                    Role role = app.login(email, password);
                    if (role == null) {
                        System.out.println("Could not login!");
                        break;
                    }

                    switch (role) {
                        case ADMIN:
                            AdminService adminService = app.getAdminService();
                            adminMenu(adminService);
                            break;
                        case USER:
                            UserService userService = app.getUserService();
                            System.out.println("You have logged in!");
                            userMenu(userService);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Input fullname: ");
                    String name = SCANNER_STR.nextLine();

                    System.out.println("Input email: ");
                    email = SCANNER_STR.nextLine();

                    System.out.println("Input password");
                    password = SCANNER_STR.nextLine();

                    boolean isRegistered = app.register(name, email, password);
                    if (isRegistered) {
                        System.out.println("You have registered!");
                    } else System.out.println("Could not register!");
                    break;
                case 3:
                    boolean loggedOut = app.logOut();
                    if (loggedOut) {
                        System.out.println("Logged out!");
                    } else System.out.println("Could not log out!");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong option!");
                    break;
            }
        }
    }

    public static void adminMenu(AdminService adminService) {
        while (true) {
            System.out.println("1. Car crud");
            System.out.println("2. Oil mark crud");
            System.out.println("3. Payment Type crud");
            System.out.println("4. Show payment history by payment type id");
            System.out.println("0. Cancel");

            System.out.println("Input option: ");
            int option = SCANNER_NUM.nextInt();

            switch (option) {
                case 1:
                    carCrud(adminService);
                    break;
                case 2:
                    oilMarkCrud(adminService);
                    break;
                case 3:
                    paymentTypeCrud(adminService);
                    break;
                case 4:
                    boolean hasTypes = adminService.showPaymentTypes();
                    if (!hasTypes) {
                        break;
                    }
                    System.out.println("\nInput payment type ID: ");
                    int id = SCANNER_NUM.nextInt();
                    boolean hasHistory = adminService.showSpecificPaymentTypeHistory(id);
                    if (!hasHistory) {
                        System.out.println("No histories yet!");
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong option!");
                    break;
            }
        }
    }

    private static void paymentTypeCrud(AdminService adminService) {
        while (true) {
            System.out.println("1. Create payment type");
            System.out.println("2. Remove payment type by ID");
            System.out.println("3. Update payment type by ID");
            System.out.println("0. Cancel");

            System.out.println("Input option: ");
            int option = SCANNER_NUM.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Input payment name: ");
                    String name = SCANNER_STR.nextLine();

                    PaymentType paymentType = new PaymentType(name);
                    boolean isAdded = adminService.addPaymentType(paymentType);
                    if (isAdded) {
                        System.out.println("Payment type added");
                    } else System.out.println("Could not add payment type!");
                    break;
                case 2:
                    boolean hasPaymentTypes = adminService.showPaymentTypes();
                    if (!hasPaymentTypes) {
                        break;
                    }
                    System.out.println("Input payment type ID: ");
                    int id = SCANNER_NUM.nextInt();
                    boolean isRemoved = adminService.removePaymentType(id);
                    if (isRemoved) {
                        System.out.println("Payment type has been removed!");
                    } else System.out.println("Could not remove payment type!");
                    break;
                case 3:
                    hasPaymentTypes = adminService.showPaymentTypes();
                    if (!hasPaymentTypes) {
                        break;
                    }
                    System.out.println("Input payment type ID: ");
                    id = SCANNER_NUM.nextInt();

                    System.out.println("Input new fee: ");
                    int newFee = SCANNER_NUM.nextInt();

                    boolean isUpdated = adminService.updatePaymentType(id, newFee);
                    if (isUpdated) {
                        System.out.println("Payment type has been updated!");
                    } else System.out.println("Could not update payment type!");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong option!");
                    break;
            }
        }
    }

    private static void oilMarkCrud(AdminService adminService) {
        while (true) {
            System.out.println("1. Create oil mark");
            System.out.println("2. Remove oil mark by ID");
            System.out.println("3. Update oil mark by ID");
            System.out.println("0. Cancel");

            System.out.println("Input option: ");
            int option = SCANNER_NUM.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Input oil mark name: ");
                    String name = SCANNER_STR.nextLine();

                    System.out.println("Input oil mark cost: ");
                    double cost = SCANNER_DOUBLE.nextInt();

                    OilMark oilMark = new OilMark(name, cost);
                    boolean isAdded = adminService.addOilMark(oilMark);
                    if (isAdded) {
                        System.out.println("Oil mark added");
                    } else System.out.println("Could not add oil mark!");
                    break;
                case 2:
                    boolean hasOilMarks = adminService.showOilMarks();
                    if (!hasOilMarks) {
                        break;
                    }
                    System.out.println("Input oil mark ID: ");
                    int id = SCANNER_NUM.nextInt();
                    boolean isRemoved = adminService.removeOilMark(id);
                    if (isRemoved) {
                        System.out.println("Oil mark has been removed!");
                    } else System.out.println("Could not remove oil mark!");
                    break;
                case 3:
                    hasOilMarks = adminService.showOilMarks();
                    if (!hasOilMarks) {
                        break;
                    }
                    System.out.println("Input oil mark ID: ");
                    id = SCANNER_NUM.nextInt();

                    System.out.println("Input oil mark new name: ");
                    String oilMarkName = SCANNER_STR.nextLine();

                    System.out.println("Input oil mark new cost: ");
                    double newCost = SCANNER_DOUBLE.nextDouble();

                    boolean isUpdated = adminService.updateOilMark(id, oilMarkName, newCost);
                    if (isUpdated) {
                        System.out.println("Oil mark has been removed!");
                    } else System.out.println("Could not oil mark type!");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong option!");
                    break;
            }
        }
    }

    private static void carCrud(AdminService adminService) {
        while (true) {
            System.out.println("1. Create car");
            System.out.println("2. Update car bu id");
            System.out.println("3. Remove car by id");
            System.out.println("0. Cancel");

            System.out.println("Input option: ");
            int option = SCANNER_NUM.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Input car brand: ");
                    String brand = SCANNER_STR.nextLine();

                    System.out.println("Input car model: ");
                    String model = SCANNER_STR.nextLine();

                    System.out.println("Input car initial fuel amount: ");
                    double initialFuelAmount = SCANNER_DOUBLE.nextDouble();

                    System.out.println("Input car fuel per 100 km: ");
                    double fuelPerKm = SCANNER_DOUBLE.nextDouble();

                    System.out.println("Input car's tank max capacity: ");
                    int maxCapacity = SCANNER_NUM.nextInt();

                    Car car = new Car(brand, model, initialFuelAmount, fuelPerKm / 100, maxCapacity);
                    boolean isCreated = adminService.addCar(car);
                    if (isCreated) {
                        System.out.println("Car has been created");
                    } else System.out.println("Could not create the car!");
                    break;
                case 2:
                    boolean hasCars = adminService.showCars();
                    if (!hasCars) {
                        break;
                    }
                    System.out.println("Input car ID: ");
                    int carId = SCANNER_NUM.nextInt();

                    System.out.println("Input new brand: ");
                    brand = SCANNER_STR.nextLine();

                    System.out.println("Input new model: ");
                    model = SCANNER_STR.nextLine();

                    boolean isUpdated = adminService.updateCar(carId, model, brand);
                    if (isUpdated) {
                        System.out.println("Car has been updated");
                    } else System.out.println("Could not update the car!");
                    break;
                case 3:
                    hasCars = adminService.showCars();
                    if (!hasCars) {
                        break;
                    }
                    System.out.println("Input car ID: ");
                    carId = SCANNER_NUM.nextInt();

                    boolean isRemoved = adminService.removeCar(carId);
                    if (isRemoved) {
                        System.out.println("Car has been removed");
                    } else System.out.println("Could not remove the car!");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong option!");
            }
        }
    }

    public static void userMenu(UserService userService) {
        while (true) {
            System.out.println("1. Show cars");
            System.out.println("2. Choose car");
            System.out.println("3. Start car");
            System.out.println("4. Stop car");
            System.out.println("5. Drive");
            System.out.println("6. Fill balance");
            System.out.println("0. Cancel");

            System.out.println("Input option: ");
            int option = SCANNER_NUM.nextInt();

            switch (option) {
                case 1:
                    boolean hasCars = userService.showCars();
                    if (!hasCars) {
                        break;
                    }
                    break;
                case 2:
                    hasCars = userService.showCars();
                    if (!hasCars) {
                        break;
                    }
                    System.out.println("Input car id: ");
                    int id = SCANNER_NUM.nextInt();
                    boolean isChosen = userService.chooseCar(id);
                    if (isChosen) {
                        System.out.println("Car chosen!");
                    } else System.out.println("Could not choose the car!");
                    break;
                case 3:
                    userService.start();
                    break;
                case 4:
                    userService.stop();
                    break;
                case 5:
                    System.out.println("Input distance: ");
                    double distance = SCANNER_DOUBLE.nextDouble();
                    userService.drive(distance);
                    break;
                case 6:
                    userService.showPaymentTypes();
                    System.out.println("Choose payment type. Input ID: ");
                    int paymentTypeId = SCANNER_NUM.nextInt();

                    System.out.println("Input sum: ");
                    double sum = SCANNER_DOUBLE.nextDouble();

                    boolean isFilled = userService.fillBalance(paymentTypeId, sum);
                    if (isFilled) {
                        System.out.println("Balance has been filled");
                    } else System.out.println("Could not fill the balance!");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong option!");
                    break;
            }
        }
    }
}
