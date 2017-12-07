package com;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.json.JSONObject;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.util.*;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ZhangShaowei on 2017/9/18 10:03
 */
//@SpringBootTest(classes = Test.class)
public class Test {

//    private static XStream xstream = new XStream(new XppDriver());


    /**
     *
     */
//    @org.junit.Test
    public void test() {

        System.err.println("123");

    }


    public static void main(String[] args) throws Exception {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);


        BigInteger bigInteger = BigInteger.valueOf(1L);

        Number number = bigInteger;

        System.out.println(number.intValue());

        System.out.println(bigInteger.intValue());

//        Map<String, Object> map = new HashMap<>();
//        map.put("1", "10");
//        map.put("2", "20");
//        map.put("3", "30");
//
//        Map<String, Number> m = map.merge();

        List<Map<String, Object>> l1 = new ArrayList<>();
        Map<String, Object> m = new HashMap<>();
        m.put("id", "1");
        m.put("time", "20");
        l1.add(m);

        Map<String, Object> n = new HashMap<>();
        n.put("id", "2");
        n.put("time", "30");
        l1.add(n);


        Map<Long, House> map = l1.stream().collect(Collectors.toMap(m1 -> Long.valueOf(m1.get("id").toString()), m2 -> {
            House house = new House();
            house.setId(m2.get("id").toString());
            house.setTime(m2.get("time").toString());
            return house;
        }));

//        Map<Long, House> map = l1.stream()
//
//
//        System.out.println(map);


//        LocalDate localDate = LocalDate.parse("2017-11-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//        LocalDate last = localDate.with(TemporalAdjusters.lastDayOfMonth());
//
//        System.out.println(last);
//        LocalDate l = last.plusDays(1);
//        System.out.println(l);
//
//        System.out.println(last.getDayOfMonth());

//        User user = new User();
//
//        Optional<User> optional = Optional.ofNullable(user);
//
//
//
//        Optional.ofNullable(user).map(User::getAddress)
//                .map(Address::getProvince)
//                .map(Province::getCity)
//                .map(City::getRegion)
//                .map(Region::getHouse)
//                .orElseThrow(Exception::new);


//        Map<String, Integer> map = new HashMap<>();
//        map.put("1", 1);
//
//        System.out.println(map.compute("2", Integer::valueOf));
////        System.out.println(map.computeIfPresent("2", Integer::valueOf));
//
//        System.err.println(map);
//        List<Person> list = new ArrayList<>();
//        list.add(new Person(1, "1", "Earth", ThreadLocalRandom.current().nextInt(10)));
//        list.add(new Person(2, "2", "Moon", ThreadLocalRandom.current().nextInt(10)));
//        list.add(new Person(3, "3", "Mars", ThreadLocalRandom.current().nextInt(10)));
//        list.add(new Person(4, "4", "Earth", ThreadLocalRandom.current().nextInt(10)));
//        list.add(new Person(5, "5", "Earth", ThreadLocalRandom.current().nextInt(10)));
//        list.add(new Person(6, "6", "Earth", ThreadLocalRandom.current().nextInt(10)));
//        list.add(new Person(7, "7", "Earth", ThreadLocalRandom.current().nextInt(10)));
//        list.add(new Person(8, "8", "Earth", ThreadLocalRandom.current().nextInt(10)));
//        list.add(new Person(9, "9", "Earth", ThreadLocalRandom.current().nextInt(10)));
//        list.add(new Person(10, "10", "Earth", ThreadLocalRandom.current().nextInt(10)));
//
//        Map<Integer, List<String>> map = list.stream().collect(
//                Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName, Collectors.toList()))
//        );
//
//        System.out.println(map);
//
//
//        String names = list.stream().map(Person::getName).collect(Collectors.joining(","));
//        System.out.println(names);


//        hello("This is zsw speaking! Who is that?", Test::callback);


    }

    public static <I, O> List<O> map(Stream<I> stream, Function<I, O> mapper) {
        return stream.reduce(new ArrayList<O>(), (acc, x) -> {
            // We are copying data from acc to new list instance. It is very inefficient,
            // but contract of Stream.reduce method requires that accumulator function does
            // not mutate its arguments.
            // Stream.collect method could be used to implement more efficient mutable reduction,
            // but this exercise asks to use reduce method.
            List<O> newAcc = new ArrayList<>(acc);
            newAcc.add(mapper.apply(x));
            return newAcc;
        }, (List<O> left, List<O> right) -> {
            // We are copying left to new list to avoid mutating it.
            List<O> newLeft = new ArrayList<>(left);
            newLeft.addAll(right);
            return newLeft;
        });
    }


    private static int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, Integer::sum);
    }

    /**
     * 线程屏障
     */
    private static void cyclic() throws Exception {

        long timer = System.currentTimeMillis();

        AtomicLong counter = new AtomicLong();

        CyclicBarrier start = new CyclicBarrier(5001);
        CyclicBarrier after = new CyclicBarrier(5001);

        for (int i = 0; i < 5000; i++) {
            Thread thread = new Thread(() -> {
                try {
                    start.await();
                    for (int j = 0; j < 100; j++) {
                        counter.incrementAndGet();
                    }
                    after.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.setDaemon(true);
            thread.start();
        }
        start.await();
        after.await();
        System.out.println(counter.get());
        System.err.println(System.currentTimeMillis() - timer);
    }


    private static void unSee() {
        System.out.println("1‬".length()); //不可见字符
        System.out.println("1".length());

        System.err.println((int) ("1‬".charAt(1)));

        char hide = (char) 8236;
        String str = "1" + String.valueOf(hide);
        System.out.println(str);
        System.out.println(str.length());
    }

    private static boolean t() {
        System.out.println(1);
        return true;
    }

    static Integer sum(Stream<Integer> stream) {
        return stream.mapToInt(num -> num).sum();
    }


    public static void hello(String message, Consumer<String> callback) {
        callback.accept(message);
    }


    public static void callback(String str) {
        System.out.println(str);
    }

    abstract static interface Dto {


        void fun(String string);


    }


    static class House {
        private String id;

        private String time;

        /**  */
        public String getId() {
            return id;
        }

        /**  */
        public void setId(String id) {
            this.id = id;
        }

        /**  */
        public String getTime() {
            return time;
        }

        /**  */
        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "House{" +
                    "id='" + id + '\'' +
                    ", time='" + time + '\'' +
                    '}';
        }
    }


    static class User {

        private Address address;

        /**  */
        public Address getAddress() {
            return address;
        }

        /**  */
        public void setAddress(Address address) {
            this.address = address;
        }
    }

    static class Address {
        private Province province;

        /**  */
        public Province getProvince() {
            return province;
        }

        /**  */
        public void setProvince(Province province) {
            this.province = province;
        }
    }

    static class Province {
        private City city;

        /**  */
        public City getCity() {
            return city;
        }

        /**  */
        public void setCity(City city) {
            this.city = city;
        }
    }

    static class City {
        private Region region;

        /**  */
        public Region getRegion() {
            return region;
        }

        /**  */
        public void setRegion(Region region) {
            this.region = region;
        }
    }

    static class Region {
        private House house;

        /**  */
        public House getHouse() {
            return house;
        }

        /**  */
        public void setHouse(House house) {
            this.house = house;
        }
    }


}
