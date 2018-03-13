package com;


import com.zsw.base.utils.RandomCodeUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
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
    @org.junit.Test
    public void test() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Callable<String> callable = () -> {
            Thread.sleep(5 * 1000);
            return "123";
        };

        Future future = executorService.submit(callable);

        if (future.isDone()) {
            System.out.println(future.get());
        }

        System.out.println("456");


    }

    @org.junit.Test
    public void random() {
        System.out.println(RandomCodeUtils.randomCode());
    }


    /**
     * @param src
     * @return
     * @throws Exception
     */
    private static String decrypt1(final String src) throws Exception {
        String tempStr = "";
        if (null != src && !"".equals(src)) {
            try {
                tempStr = DesBase64.decrypt(src);
            } catch (Exception e) {
                throw new Exception("解密失败");
            }
        }
        return tempStr;
    }

    public static void build(final TempDayPlanDto planDto, final DayOfMonthData data) {
        planDto.addNumber(data.getTotal());
        if (data.getUnfinished() > 0) {
            planDto.setType(0);
        }
    }

//    public static TempDayPlanDto build(List<DayOfMonthData> data) {
//        TempDayPlanDto tempDayPlanDto = new TempDayPlanDto(data.get(0).getDayOfMonth(), 0, 0);
//        data.forEach(dayOfMonthData -> tempDayPlanDto.addNumber(dayOfMonthData.getTotal()));
//        return tempDayPlanDto;
//    }

    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String format(final Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(TIMESTAMP_FORMAT);
    }

    public static Date format(final String dateTime) {
        return Date.from(LocalDateTime.parse(dateTime, TIMESTAMP_FORMAT).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void main(String[] args) throws Exception {

        LocalDate localDate = LocalDate.now();

        LocalDateTime localDateTime = localDate.atTime(23, 59, 59);

        System.err.println(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
        System.err.println(Date.from(localDateTime.toLocalDate().atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant()));


    }


    /**
     * 分组分块聚合
     */
    private static void model() {
        //        List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7");
//
//        String str1 = list.stream().collect(Collectors.joining(",", "[", "]"));
//        System.out.println("str1: " + str1);


//        StringBuilder str2 = list.stream()
//                .reduce(new StringBuilder(),
//                        (builder, string) -> {
//                            if (builder.length() > 0) {
//                                builder.append(",");
//                            }
//                            builder.append(string);
//                            return builder;
//                        },
//                        (left, right) -> {return left.append(right);});

//        String str3 = list.stream()
//                .collect(Collectors.reducing("", Function.identity(), (left, right) -> left  + "," + right));

//        String str3 = list.stream().reduce("", (left, right) -> left + "," + right);

//        Set set1 = list.stream().collect(Collectors.reducing(new HashSet(),
//                (string) -> {
//                    HashSet s = new HashSet<>();
//                    s.add(string);
//                    return s;
//                }, (left, right) -> {
//                    left.addAll(right);
//                    return left;
//                }));

//        Set set1 = list.stream().reduce(
//                new HashSet(),
//                (set, string) -> {
//                    set.add(string);
//                    return set;
//                },
//                (left, right) -> right
//        );
//
//        System.out.println("set1: " + set1.toString());


//        DayOfMonthData data1 = new DayOfMonthData();
//        data1.setDayOfMonth(1);
//        data1.setTotal(1);
//        data1.setUnfinished(1);
//
//        DayOfMonthData data2 = new DayOfMonthData();
//        data2.setDayOfMonth(2);
//        data2.setTotal(2);
//        data2.setUnfinished(2);
//
//        Map<Integer, DayOfMonthData> map1 = Arrays.asList(data1, data2).stream()
//                .collect(Collectors.toMap(DayOfMonthData::getDayOfMonth, Function.identity()));
//
//
//        DayOfMonthData data3 = new DayOfMonthData();
//        data3.setDayOfMonth(2);
//        data3.setTotal(3);
//        data3.setUnfinished(3);
//
//        Map<Integer, DayOfMonthData> map2 = Collections.singletonMap(data3.getDayOfMonth(), data3);
//
//        // 分组
//        Map<Integer, List<DayOfMonthData>> gmap = Stream.of(map1, map2)
//                .flatMap(map -> map.entrySet().stream())
//                .map(HashMap.Entry::getValue)
//                .collect(Collectors.groupingBy(DayOfMonthData::getDayOfMonth));
//
//        System.out.println("gmap: " + gmap);
//
//        // 分块
//        Map<Boolean, List<DayOfMonthData>> bmap = Stream.of(map1, map2).flatMap(map -> map.entrySet().stream())
//                .map(HashMap.Entry::getValue)
//                .collect(
//                        Collectors.partitioningBy(dayOfMonthData -> dayOfMonthData.getTotal() > 1)
//                );
//        System.out.println("bmap: " + bmap);

        // 聚合
//        TempDayPlanDto d = Stream.of(map1, map2)
//                .flatMap(map -> map.entrySet().stream())
//                .map(HashMap.Entry::getValue)
//                .collect(Collectors.reducing(new TempDayPlanDto(),
//                        (data) -> new TempDayPlanDto(data.getDayOfMonth(), data.getTotal(), 0),
//                        (left, right) -> {
//                            left.addNumber(right.getNumber());
//                            return left;
//                        }));
//
//        System.err.println(d.getNumber());

//        Map<Integer, TempDayPlanDto> smap = Stream.of(map1, map2)
//                .flatMap(map -> map.entrySet().stream())
//                .map(HashMap.Entry::getValue)
//                .collect(
//                        Collectors.groupingBy(
//                                DayOfMonthData::getDayOfMonth,
//                                Collectors.mapping(
//                                        dto -> new TempDayPlanDto(dto.getDayOfMonth(), dto.getTotal(), 0),
//                                        Collectors.reducing(
//                                                new TempDayPlanDto(),
//                                                (left, right) -> {
//                                                    right.addNumber(left.getNumber());
//                                                    return right;
//                                                }
//                                        )
//                                )
//                        )
//                );
//
//        smap.forEach((key, value) -> {
//            System.out.println(key + " : " + value.toString());
//        });

//        Map<Integer,TempDayPlanDto> tmap = Stream.of(map1, map2)
//                .flatMap(map -> map.entrySet().stream())
//                .map(HashMap.Entry::getValue)
//                .collect(
//                        Collectors.groupingBy(
//                                DayOfMonthData::getDayOfMonth,
//                                Collectors.reducing(
//                                        new TempDayPlanDto(),
//                                        (data) -> new TempDayPlanDto(data.getDayOfMonth(), data.getTotal(), 0),
//                                        (left, right) -> {
//                                            left.setDayOfMonth(right.getDayOfMonth());
//                                            left.addNumber(right.getNumber());
//                                            return left;
//                                        }
//                                )
//                        )
//                );
//
//        tmap.forEach((key, value) -> {
//            System.out.println(key + " : " + value.toString());
//        });
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


    @org.junit.Test
    public void unSee() {
        System.err.println(StringUtils.hasText("‬"));
        System.out.println("1‬".length()); //不可见字符
        System.out.println("1".length());

        char hide = (char) 8236;
        String str = "1" + String.valueOf(hide);
        System.out.println(str);
        System.out.println(str.length());
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

    static class DtoTest {
        private Long numner;

        /**  */
        public Long getNumner() {
            return numner;
        }

        /**  */
        public void setNumner(Long numner) {
            this.numner = numner;
        }
    }

}
