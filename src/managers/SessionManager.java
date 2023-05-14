package managers;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import exceptions.noPermissionException;
import exceptions.userDoesNotExistException;
import exceptions.userNameTakenException;
import exceptions.wrongCardDetailsException;
import location.FuelStation;
import location.PointOfInterest;
import location.Route;
import user.PaymentCard;
import user.Session;
import user.User;
import user.UserType;
import vehicle.Car;
import vehicle.CarGenerator;
import vehicle.FuelType;

/**
 * this class will be used to manage the session, users and points of interest
 */
public class SessionManager {
    private static SessionManager instance;
    private Session session;
    private UserManager userManager;
    private POIManager poiManager;
    // private POIManager poiManager;

    private SessionManager() {
        userManager = UserManager.getInstance();
        poiManager = POIManager.getInstance();
        session = Session.getInstance();
    }

    /**
     * returns the instance of the SessionManager
     *
     * @return
     */
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    /**
     * sets the current user in session to the user with the given username and
     * password
     *
     * @param username string
     * @param password string
     * @return true if login successful, false otherwise
     */
    public boolean login(String username, String password) {
        User user = userManager.getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            session.setCurrentUser(user);
            return true;
        }
        return false;
    }

    /**
     * sets the current user in session to null
     *
     * @param user User
     */
    public void logout() {
        session.setCurrentUser(null);
    }

    /**
     * checks if the current user in session is logged in
     *
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return session.getCurrentUser() != null;
    }

    /**
     * checks if the current user in session is an admin
     *
     * @return true if admin, false otherwise
     */
    public boolean isAdmin() {
        return session.getCurrentUser().getUserType() == UserType.ADMIN;
    }

    /**
     * returns currently logged in user
     *
     * @return User
     */
    public User getCurrentUser() {
        return session.getCurrentUser();
    }

    /**
     * returns the username of the currently logged in user
     *
     * @return String
     */
    public String getCurrentUsername() {
        return session.getCurrentUser().getUserName();
    }

    /**
     * changes the username of the given user if the new username is not taken
     *
     * @return true if username changed, false otherwise
     */
    public boolean changeUserName(String newUsername) {
        try {
            userManager.changeUserName(getCurrentUser(), newUsername);
        } catch (userDoesNotExistException | userNameTakenException e) {
            return false; // username not changed
        }
        return true; // username changed
    }

    /**
     * registers a new user with the given parameters
     *
     * @param userName
     * @param email
     * @param password
     * @param dateOfBirth
     * @param gender
     * @return
     */
    public boolean registerUser(String userName, String email, String password, LocalDate dateOfBirth, String gender) {
        try {
            userManager.registerUser(UserType.BASIC_USER, userName, email, password, dateOfBirth, gender, null);
            System.out.println("dateOfBirth " + dateOfBirth);
        } catch (userNameTakenException e) {
            return false; // user not registered
        }
        session.setCurrentUser(userManager.getUser(userName));
        return true;
    }

    /**
     * registers a new admin with the given parameters
     *
     * @param userName
     * @param email
     * @param password
     * @param dateOfBirth
     * @param gender
     * @return true if registration successful, false otherwise
     */
    public boolean registerAdmin(String userName, String email, String password, LocalDate dateOfBirth, String gender) {
        try {
            userManager.registerUser(UserType.ADMIN, userName, email, password, dateOfBirth, gender, null);
        } catch (userNameTakenException e) {
            return false; // user not registered
        }
        // session.setCurrentUser(userManager.getUser(in.getUserName()));
        return true;
    }

    public boolean changeToPremium(User in, String cardNumber, LocalDate expirationDate, String securityCode) {
        if (in.getUserType() == UserType.PREMIUM_USER || in.getUserType() == UserType.ADMIN) {
            return true; // already of type userType or admin
        }
        try {
            userManager.changeToPremium(in, cardNumber, expirationDate, securityCode);
        } catch (userDoesNotExistException e) {
            return false;
        } catch (wrongCardDetailsException e) {
            return false;
        }
        return true;
    }

    /**
     * changes the type of the given user to the given type
     *
     * @param in
     * @param admin
     * @param userType
     * @return true if type changed, false otherwise
     */
    public boolean changeUserType(User in, User admin, UserType userType) {
        if (in.getUserType() == userType || in.getUserType() == UserType.ADMIN) {
            return false;
        }

        try {
            if (userType == UserType.ADMIN) {
                userManager.changeToAdmin(in, admin);
            } else if (userType == UserType.PREMIUM_USER) {
                userManager.changeToPremium(in, admin);
            }
        } catch (noPermissionException | userDoesNotExistException e) {
            return false;
        }
        return true;
    }

    /**
     * checks if the given credit card details are valid
     *
     * @param creditCardNumber
     * @param creditCardExpirationDate
     * @param creditCardSecurityCode
     * @return true if valid, false otherwise
     */
    public boolean checkCardDetails(String creditCardNumber, LocalDate creditCardExpirationDate,
            String creditCardSecurityCode) {
        try {
            new PaymentCard(creditCardNumber, creditCardExpirationDate, creditCardSecurityCode);
        } catch (wrongCardDetailsException e) {
            return false;
        }
        return true;
    }

    /**
     * registers a new premium user with the given parameters
     *
     * @param userName
     * @param email
     * @param password
     * @param dateOfBirth
     * @param gender
     * @param creditCardNumber
     * @param creditCardExpirationDate
     * @param creditCardSecurityCode
     * @throws wrongCardDetailsException
     * @return true if registration successful, false otherwise
     */
    public boolean registerPremiumUser(String userName, String email, String password, LocalDate dateOfBirth,
            String gender, String creditCardNumber, LocalDate creditCardExpirationDate, String creditCardSecurityCode) {

        // let it throw exception if card details are wrong to handle it in the gui by
        // showing a message
        PaymentCard card;
        try {
            card = new PaymentCard(creditCardNumber, creditCardExpirationDate, creditCardSecurityCode);
        } catch (wrongCardDetailsException e) {
            return false;
        }

        try {
            userManager.registerPremiumUser(UserType.PREMIUM_USER, userName, email, password, dateOfBirth, gender, null,
                    card);
        } catch (userNameTakenException e) {
            return false; // user not registered
        }

        session.setCurrentUser(userManager.getUser(userName));
        return true;
    }

    /**
     * deletes the currently logged in user
     *
     * @return true if user deleted, false otherwise
     */
    public boolean deleteUser() {
        try {
            userManager.deleteUser(getCurrentUser());
        } catch (userDoesNotExistException e) {
            return false;
        }
        return true;
    }

    /**
     * deletes the given user
     *
     * @param in User to be deleted
     * @return true if user deleted, false otherwise
     */
    public boolean deleteUser(User in) {
        try {
            userManager.deleteUser(in);
        } catch (userDoesNotExistException e) {
            return false;
        }
        return true;
    }

    /**
     * deletes the given user if the admin is logged in
     *
     * @param in
     * @param admin
     * @return true if user deleted, false otherwise
     * @throws noPermissionException
     */
    public boolean deleteUser(User in, User admin) throws noPermissionException {
        if (admin.getUserType() != UserType.ADMIN) {
            throw new noPermissionException();
        }
        if (in.getUserType() == UserType.ADMIN) {
            throw new noPermissionException(); // admin cannot delete another admin
        }
        try {
            userManager.deleteUser(in);
        } catch (userDoesNotExistException e) {
            return false;
        }
        return true;
    }

    /**
     * adds a car to the given user with the given parameters
     *
     * @param user
     * @param year
     * @param licenseNumber
     * @param model
     * @param fuel
     * @param fuelConsumption
     * @param maximumRange
     * @param currentFuelLevel
     * @param location
     * @return true if car added, false otherwise
     */
    public boolean addCarToUser(int year, String licenseNumber, String model, FuelType fuel,
            double fuelConsumption, int fuelTankCapacity, int currentFuelLevel) {
        try {
            userManager.addCarToUser(getCurrentUser(), year, licenseNumber, model, fuel, fuelConsumption,
                    fuelTankCapacity, currentFuelLevel);
        } catch (userDoesNotExistException e) {
            return false;
        }
        return true;
    }

    /**
     * adds a random car to the given user
     *
     * @param in user to add car to
     * @return true if car added, false otherwise
     */
    public boolean generateRandomCar(User in) {
        try {
            Car tempCar = CarGenerator.generateRandomCar();
            userManager.addCarToUser(in, tempCar.getYear(), tempCar.getLicenseNumber(),
                    tempCar.getModel(), tempCar.getFuelType(), tempCar.getFuelConsumption(),
                    tempCar.getFuelTankCapacity(), tempCar.getCurrentFuelLevel());
        } catch (userDoesNotExistException e) {
            return false; // user not registered
        }
        return true;
    }

    /**
     * gets all users registered in the system
     *
     * @return map of all users registered in the system
     */
    public Map<String, User> getUsers() {
        return userManager.getRegisteredUsers();
    }

    // ------------------------------------------------POI------------------------------------------------

    /**
     * calculates the shortest route between two POIs with the given car and returns
     * a list of routes
     *
     * @param start       POI to start from
     * @param destination POI to end at
     * @param car         car to use
     * @return list of routes
     */
    public List<Route> calculateShortestRoute(PointOfInterest start, PointOfInterest destination, Car car) {
        return poiManager.findRoute(start, destination, car);
    }

    /**
     * changes the strategy of the POI manager to the given strategy
     * strategy can be "Shortest" or "Cheapest"
     *
     * @param strategy strategy to change to
     * @return true if strategy changed, false otherwise
     */
    public boolean changeStrategy(String strategy) {
        if (strategy.equals("Shortest")) {
            poiManager.changeStrategy(true);
            return true;
        } else if (strategy.equals("Cheapest")) {
            poiManager.changeStrategy(false);
            return true;
        }
        return false;
    }

    /**
     * returns a list of all POIs sorted by fuel stations first
     * also has a comparator to sort the list by fuel stations first
     *
     * @return list of all POIs sorted by fuel stations first
     */
    public List<PointOfInterest> getPOIs() {
        Comparator<PointOfInterest> poiComparator = new Comparator<PointOfInterest>() {
            @Override
            public int compare(PointOfInterest poi1, PointOfInterest poi2) {
                if (poi1 instanceof FuelStation && !(poi2 instanceof FuelStation)) {
                    return -1;
                } else if (!(poi1 instanceof FuelStation) && poi2 instanceof FuelStation) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
        List<PointOfInterest> sortedPOIs = poiManager.getPOIs();
        sortedPOIs.sort(poiComparator);
        return sortedPOIs;
    }

    /**
     * returns list of all POIs which are needed to be visited to get from start to
     * destination
     *
     * @param start       POI to start from
     * @param destination POI to end at
     * @param car         car to use
     * @return list of all POIs which are needed to be visited to get from start to
     */
    public List<Route> navigate(String start, String destination, Car car) {
        if (start == null || destination == null || car == null) {
            return null;
        }
        Map<String, PointOfInterest> POIsMap = poiManager.getPOIsMap();
        PointOfInterest startPOI = POIsMap.get(start);
        PointOfInterest destinationPOI = POIsMap.get(destination);

        return poiManager.findRoute(startPOI, destinationPOI, car);
    }
}
