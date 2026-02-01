package root.fakeDB;

import root.model.entity.core.*;
import root.model.entity.menu.*;
import root.model.entity.order.detail.*;
import root.model.enums.OrderStatus;
import root.model.enums.TableOrderStatus;
import root.model.enums.TableStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class FakeData {
    public static final List<Area> areas = new ArrayList<>();
    public static final List<Customer> customers = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
    public static final List<TableEntity> tableEntities = new ArrayList<>();


    public static final List<Food> foods = new ArrayList<>();
    public static final List<FoodAddon> foodAddons = new ArrayList<>();
    public static final List<FoodCategory> foodCategories = new ArrayList<>();
    public static final List<OptionRequired> optionRequireds = new ArrayList<>();
    public static final List<OptionRequiredGroup> optionRequiredGroups = new ArrayList<>();


    public static final List<FoodAddonItem> foodAddonItems = new ArrayList<>();
    public static final List<OptionRequiredItem> optionRequiredItems = new ArrayList<>();
    public static final List<OrderItem> orderItems = new ArrayList<>();
    public static final List<TableOrder> tableOrders = new ArrayList<>();

    public static void init(){
        fakeAreaAndTable();
        fakeCustomer();
        fakeUser();
        fakeMenu();
        fakeOrder();
    }

    private static void fakeAreaAndTable(){
        Area area1 = Area.builder().id(1).name("Tầng 1").build();
        Area area2 = Area.builder().id(2).name("Tầng 2").build();
        areas.add(area1);
        areas.add(area2);

        for(int i = 1; i <= 3;i++){
            TableEntity table = TableEntity.builder()
                    .id(i)
                    .name("Bàn " + i)
                    .status(TableStatus.AVAILABLE)
                    .area(area1)
                    .build();

            area1.getTableList().add(table);
            tableEntities.add(table);
        }

        for(int i = 4; i <= 8;i++){
            TableEntity table = TableEntity.builder()
                    .id(i)
                    .name("Bàn " + i)
                    .status(TableStatus.AVAILABLE)
                    .area(area2)
                    .build();

            area2.getTableList().add(table);
            tableEntities.add(table);
        }


    }

    private static void fakeCustomer(){
        for(int i = 1;i <= 10;i++){
            customers.add(Customer.builder()
                            .id(i)
                            .name("Khách " + i)
                            .phoneNumber("012345678" + i)
                            .build());
        }
    }

    private static void fakeUser(){
        users.add(User.builder()
                        .id(1)
                        .firstName("Admin1")
                        .lastName("System")
                        .username("admin1")
                        .email("admin1@gamil.com")
                        .password("12345")
                        .build());

    }
    private static void fakeMenu(){
        User admin = users.get(0);

        FoodCategory mainDish = FoodCategory.builder()
                .id(1)
                .name("Món chính")
                .sortOrder(1)
                .active(true)
                .user(admin)
                .build();

        FoodCategory drink = FoodCategory.builder()
                .id(2)
                .name("Đồ uống")
                .sortOrder(2)
                .active(true)
                .user(admin)
                .build();

        foodCategories.add(mainDish);
        foodCategories.add(drink);

        admin.getFoodCategories().add(mainDish);
        admin.getFoodCategories().add(drink);

        Food phoBo = Food.builder()
                .id(1)
                .name("Phở bò")
                .price(new BigDecimal("45000"))
                .active(true)
                .category(mainDish)
                .description("Phở bò truyền thống")
                .build();


        Food comRang = Food.builder()
                .id(3)
                .name("Cơm rang")
                .price(new BigDecimal("35000"))
                .active(true)
                .category(mainDish)
                .build();

        Food traChanh = Food.builder()
                .id(4)
                .name("Trà chanh")
                .price(new BigDecimal("15000"))
                .active(true)
                .category(drink)
                .build();

        Food coca = Food.builder()
                .id(5)
                .name("Coca Cola")
                .price(new BigDecimal("20000"))
                .active(true)
                .category(drink)
                .build();

        OptionRequiredGroup sizeGroup = OptionRequiredGroup.builder()
                .id(1)
                .name("Chọn size")
                .required(true)
                .food(phoBo)
                .build();

        optionRequiredGroups.add(sizeGroup);
        phoBo.getOptionRequiredGroups().add(sizeGroup);

        OptionRequired sizeNho = OptionRequired.builder()
                .id(1)
                .name("Nhỏ")
                .price(0)
                .optionRequiredGroup(sizeGroup)
                .build();

        OptionRequired sizeLon = OptionRequired.builder()
                .id(2)
                .name("Lớn")
                .price(10000)
                .optionRequiredGroup(sizeGroup)
                .build();

        optionRequireds.add(sizeNho);
        optionRequireds.add(sizeLon);

        sizeGroup.getOptionsRequired().add(sizeNho);
        sizeGroup.getOptionsRequired().add(sizeLon);


        foods.addAll(List.of(phoBo, comRang, traChanh, coca));
        mainDish.getFoods().addAll(List.of(phoBo, comRang));
        drink.getFoods().addAll(List.of(traChanh, coca));

        FoodAddon trung = FoodAddon.builder()
                .id(1)
                .name("Trứng")
                .price(5000)
                .food(phoBo)
                .build();

        FoodAddon boVien = FoodAddon.builder()
                .id(2)
                .name("Bò viên")
                .price(10000)
                .food(phoBo)
                .build();

        FoodAddon lapXuong = FoodAddon.builder()
                .id(3)
                .name("Lạp xưởng")
                .price(8000)
                .food(comRang)
                .build();

        foodAddons.addAll(List.of(trung, boVien, lapXuong));
        phoBo.getFoodAddons().addAll(List.of(trung, boVien));
        comRang.getFoodAddons().add(lapXuong);
    }

    public static void fakeOrder(){
        TableOrder tableOrder1 = TableOrder.builder()
                .id(1)
                .status(TableOrderStatus.OPEN)
                .openedAt(LocalDateTime.now())
                .table(tableEntities.get(0))
                .build();

        tableOrders.add(tableOrder1);
        tableEntities.get(0).setStatus(TableStatus.OCCUPIED);

        // Order
        Order order1 = Order.builder()
                .id(1)
                .createTime(new Date())
                .customerCount(2)
                .note("Không hành")
                .status(OrderStatus.DOING)
                .user(users.get(0))
                .tableOrder(tableOrder1)
                .build();

        orders.add(order1);
        tableOrder1.getOrders().add(order1);

        OrderItem itemPho = OrderItem.builder()
                .id(1)
                .order(order1)
                .food(foods.get(0))
                .quantity(1)
                .priceAtOrder(foods.get(0).getPrice())
                .build();

        orderItems.add(itemPho);
        order1.getOrderItemList().add(itemPho);

        OptionRequiredItem optionItem = OptionRequiredItem.builder()
                .id(1)
                .orderItem(itemPho)
                .optionRequired(optionRequireds.get(1))
                .priceAtOrder(optionRequireds.get(1).getPrice())
                .build();

        optionRequiredItems.add(optionItem);
        itemPho.getOptions().add(optionItem);

        FoodAddonItem addon1 = FoodAddonItem.builder()
                .id(1)
                .orderItem(itemPho)
                .foodAddon(foodAddons.get(0))
                .quantity(1)
                .priceAtOrder(foodAddons.get(0).getPrice())
                .build();

        foodAddonItems.add(addon1);
        itemPho.getAddons().add(addon1);

        TableOrder tableOrder2 = TableOrder.builder()
                .id(2)
                .status(TableOrderStatus.OPEN)
                .openedAt(LocalDateTime.now())
                .table(tableEntities.get(1))
                .build();

        tableOrders.add(tableOrder2);
        tableEntities.get(1).setStatus(TableStatus.OCCUPIED);

        Order order2 = Order.builder()
                .id(2)
                .createTime(new Date())
                .customerCount(3)
                .note("Ít đá")
                .status(OrderStatus.DOING)
                .user(users.get(0))
                .tableOrder(tableOrder2)
                .build();

        orders.add(order2);
        tableOrder2.getOrders().add(order2);
    }
}
