package uz.online.pdp.model;

import uz.online.pdp.Role;
import uz.online.pdp.service.AdminService;
import uz.online.pdp.service.UserService;

import java.util.*;

public class App {
    private AdminServiceImpl adminService;
    private UserServiceImpl userService;

    private User currentUser;
    private User superAdmin;

    private List<User> users;
    private List<Car> cars;
    private List<OilMark> oilMarks;
    private List<PaymentType> paymentTypes;

    private Map<Integer, List<PaymentHistory>> paymentHistories;

    public App() {
        users = new ArrayList<>();
        cars = new ArrayList<>();
        oilMarks = new ArrayList<>();
        paymentTypes = new ArrayList<>();

        paymentHistories = new LinkedHashMap<>();

        superAdmin = new User(Role.ADMIN, "Bekhruz", "bexruz@mail.ru", "0000");
        users.add(superAdmin);
    }

    public Role login(String email, String password) {
        Role role = null;
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                this.currentUser = user;
                role = user.role;
                break;
            }
        }

        if (role == null) return null;
        if (role == Role.ADMIN) adminService = new AdminServiceImpl();
        else userService = new UserServiceImpl();

        return role;
    }

    public boolean register(String fullName, String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }

        User user = new User(Role.USER, fullName, email, password);
        this.users.add(user);
        return true;
    }

    public boolean logOut() {
        if (currentUser == null) return false;

        if (currentUser.role == Role.ADMIN) adminService = null;
        else userService = null;

        currentUser = null;
        return true;
    }

    public AdminServiceImpl getAdminService() {
        return this.adminService;
    }

    public UserServiceImpl getUserService() {
        return this.userService;
    }

    private class AdminServiceImpl implements AdminService {
        @Override
        public boolean showCars() {
            if (cars.isEmpty()) {
                System.out.println("No cars yet!");
                return false;
            }

            for (Car car : cars) {
                System.out.println(car);
            }
            return true;
        }

        @Override
        public boolean addCar(Car car) {
            if (car == null) {
                return false;
            }
            cars.add(car);
            return true;
        }

        @Override
        public boolean removeCar(int carId) {
            Iterator<Car> carIterator = cars.iterator();
            while (carIterator.hasNext()) {
                Car next = carIterator.next();
                if (next.id == carId) {
                    carIterator.remove();
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean updateCar(int carId, String newModel, String newBrand) {
            for (Car car : cars) {
                if (car.id == carId) {
                    car.setModel(newModel);
                    car.setBrand(newBrand);
                    return true;
                }
            }

            return false;
        }


        @Override
        public boolean showOilMarks() {
            if (oilMarks.isEmpty()) {
                System.out.println("No oil marks yet!");
                return false;
            }

            for (OilMark oilMark : oilMarks) {
                System.out.println(oilMark);
            }
            return true;
        }

        @Override
        public boolean addOilMark(OilMark oilMark) {
            if (oilMark == null) return false;
            oilMarks.add(oilMark);
            return true;
        }

        @Override
        public boolean removeOilMark(int oilMarkId) {
            Iterator<OilMark> oilMarkIterator = oilMarks.iterator();
            while (oilMarkIterator.hasNext()) {
                OilMark next = oilMarkIterator.next();
                if (next.id == oilMarkId) {
                    oilMarkIterator.remove();
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean updateOilMark(int oilMarkId, String newMark, double newCost) {
            for (OilMark oilMark : oilMarks) {
                if (oilMark.id == oilMarkId) {
                    oilMark.setMark(newMark);
                    oilMark.setCost(newCost);
                    return true;
                }
            }
            return false;
        }


        @Override
        public boolean showPaymentTypes() {
            if (paymentTypes.isEmpty()) {
                System.out.println("No payment types yet!");
                return false;
            }

            for (PaymentType paymentType : paymentTypes) {
                System.out.println(paymentType);
            }
            return true;
        }

        @Override
        public boolean addPaymentType(PaymentType paymentType) {
            if (paymentType == null) return false;
            paymentTypes.add(paymentType);
            paymentHistories.put(paymentType.id, new ArrayList<>());
            return true;
        }

        @Override
        public boolean removePaymentType(int paymentTypeId) {
            Iterator<PaymentType> carIterator = paymentTypes.iterator();
            while (carIterator.hasNext()) {
                PaymentType next = carIterator.next();
                if (next.id == paymentTypeId) {
                    carIterator.remove();
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean updatePaymentType(int paymentTypeId, int newFee) {
            return false;
        }


        @Override
        public boolean showSpecificPaymentTypeHistory(int paymentTypeId) {
            return true;
        }
    }

    private class UserServiceImpl implements UserService {
        private Car chosenCar;

        @Override
        public boolean showCars() {
            if (cars.isEmpty()) {
                System.out.println("No cars yet!");
                return false;
            }
            for (Car car : cars) {
                System.out.println(car);
            }
            return true;
        }

        @Override
        public boolean chooseCar(int carId) {
            if (cars.isEmpty()) {
                return false;
            }

            for (Car car : cars) {
                if (car.id == carId) {
                    chosenCar = car;
                    return true;
                }
            }
            return false;
        }

        @Override
        public void start() {
            if (chosenCar == null) {
                System.out.println("Choose car first!");
                return;
            }

            chosenCar.start();
        }

        @Override
        public void stop() {
            if (chosenCar == null) {
                System.out.println("Choose car first!");
                return;
            }

            chosenCar.stop();
        }

        @Override
        public void drive(double distance) {
            if (chosenCar == null) {
                System.out.println("Choose car first!");
                return;
            }

            chosenCar.drive(distance);
        }


        @Override
        public boolean fillTank(int paymentTypeId, int oilMarkId, int litreQuantity) {
            if (chosenCar == null) return false;
            PaymentType paymentType = null;
            OilMark oilMark = null;
            for (PaymentType type : paymentTypes) {
                if (type.id == paymentTypeId) {
                    paymentType = type;
                    break;
                }
            }
            for (OilMark mark : oilMarks) {
                if (mark.id == oilMarkId) {
                    oilMark = mark;
                    break;
                }
            }

            return true;
        }

        @Override
        public boolean fillBalance(int paymentTypeId, double sum) {
            PaymentType paymentType = null;
            for (PaymentType type : paymentTypes) {
                if (type.id == paymentTypeId) {
                    paymentType = type;
                    break;
                }
            }
            return true;
        }

        @Override
        public boolean showOilMarks() {
            if (oilMarks.isEmpty()) {
                System.out.println("No oil marks yet!");
                return false;
            }

            for (OilMark oilMark : oilMarks) {
                System.out.println(oilMark);
            }
            return true;
        }

        @Override
        public boolean showPaymentTypes() {
            if (paymentTypes.isEmpty()) {
                System.out.println("No payment types yet!");
                return false;
            }

            for (PaymentType paymentType : paymentTypes) {
                System.out.println(paymentType);
            }

            return true;
        }
    }
}
