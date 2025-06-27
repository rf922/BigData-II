/**
 * File : ProductBigDataQuestions.java
 */
package ChemData;

import java.util.*;
import java.util.stream.*;
import java.io.*;

public class ProductBigDataQuestions {

    public static List<Product> productList;
    public static Map<String, List<Product>> chemicalProductMap;

    public static void main(String[] args) {
        productList = createList();

        int numberOfProducts = productList.size();
        System.out.println("Number of products \t\t\t\t\t73548: " + numberOfProducts);

        // QUESTION 1: How many total chemicals appear across all products? 
        // Example: Product1 contains Chemical1, Chemical2 and Product2 contains Chemical2 and Chemical3
        //          count would be 4 total chemicals
        int numberChemicalsInAllProducts = q1();
        System.out.println("Number of chemicals in all products \t\t\t81790: " + numberChemicalsInAllProducts);

        // QUESTION 2: How many different companies are in the dataset?
        int numberCompanies = q2();
        System.out.println("Number of companies \t\t\t\t\t549: " + numberCompanies);

        // QUESTION 3: How many products have 4 or more chemicals?
        long numberProductsFourOrMore = q3();
        System.out.println("Number of products with 4 or more chemicals \t\t193 :" + numberProductsFourOrMore);

        //  QUESTION 4: Create a Map with key = company name and value = list of products for that company.
        //  Hint: use Collectors.groupingBy!
        Map<String, List<Product>> companyProductMap = q4();
        // checks that the map is correct; consider adding additional checks!
        System.out.println("Number of companies (keys) \t\t\t\t549: " + companyProductMap.size());
        System.out.println("Number of products for Aloette Cosmetics Inc. \t\t77: " + (companyProductMap.get("Aloette Cosmetics Inc.")).size());
        System.out.println("Number of products for Yves Rocher Inc. \t\t416: " + (companyProductMap.get("Yves Rocher Inc.")).size());
        System.out.println("Number of products for label.m USA INC \t\t\t4: " + (companyProductMap.get("label.m USA INC")).size());

        // QUESTION 5: Use the map you created above. Which company has the most products?
        // Hint: Use .max(Comparator) and define the Comparator to compare companies based on size of their lists.
        String companyMostProducts = q5(companyProductMap);
        System.out.println("Company with most products \t\t\t\tLOreal USA: " + companyMostProducts);

        // Use this map for the next questions. 
        // This is a map with key = chemical name and value = list of products that contain that chemical.
        // This code creates a map with all chemicals and empty lists.
        chemicalProductMap = new HashMap<String, List<Product>>();
        productList.stream().forEach(
                product -> {
                    product.getChemicals().stream().
                            forEach(chemicalName
                                    -> chemicalProductMap.putIfAbsent(chemicalName, new ArrayList<Product>()));
                }
        );

        // QUESTION 6: Fill the lists (the value) of the map above.
        // Hint: Use a nested stream (one stream of productList and then a separate stream for each list of each product).
        q6(chemicalProductMap);
        // checks that the map is correct; consider adding additional checks!
        System.out.println("Number of products that contain Formaldehyde (gas) \t\t121: " + (chemicalProductMap.get("Formaldehyde (gas)")).size());
        System.out.println("Number of products that contain Lauramide DEA \t\t\t20: " + (chemicalProductMap.get("Lauramide DEA")).size());
        System.out.println("Number of products that contain Arsenic (inorganic oxides) \t1: " + (chemicalProductMap.get("Arsenic (inorganic oxides)")).size());

        // QUESTION 7: Which chemical appears in the most products?
        // Hint: use max(Comparator) again. Define your comparator to compare chemical names based on the size of the list of products.
        String mostOccurringChemical = q7();
        System.out.println("Most common chemical \t\t\t\t\t\tTitanium dioxide: " + mostOccurringChemical);
        System.out.println("It appears in this many products \t\t\t\t67897: " + (chemicalProductMap.get("Titanium dioxide")).size());

        //Bonus question which product contains the most chemicals ?
        System.out.println("Product With Most Chemicals\t\t\t\t" + bonusq());
    }

    /**
     * QUESTION 1: How many total chemicals appear across all products? Example:
     * Product1 contains Chemical1, Chemical2 and Product2 contains Chemical2
     * and Chemical3 count would be 4 total chemicals
     *
     * @return int
     */
    public static int q1() {
        return productList.stream()
                .distinct()
                .map(Product::getChemicals)
                .mapToInt(List::size)
                .sum();
    }

    /**
     * QUESTION 2: How many different companies are in the dataset?
     *
     * @return int
     */
    public static int q2() {
        return productList.stream()
                .map(Product::getCompany)
                .distinct()
                .mapToInt(x -> 1)
                .sum();
    }

    /**
     * QUESTION 3: How many products have 4 or more chemicals?
     *
     * @return long
     */
    public static long q3() {
        return productList.stream()
                .map(Product::getChemicals)
                .filter(x -> x.size() >= 4)
                .count();
    }

    /**
     * QUESTION 4: Create a Map with key = company name and value = list of
     * products for that company. Hint: use Collectors.groupingBy
     *
     * @return Map<String, List<Product>>
     */
    public static Map<String, List<Product>> q4() {
        return productList.stream()
                .collect(Collectors.groupingBy(Product::getCompany));

    }

    /**
     * QUESTION 5: Use the map you created above. Which company has the most
     * products? Hint: Use .max(Comparator) and define the Comparator to compare
     * companies based on size of their lists.
     *
     * @param companyProductMap
     * @return String
     */
    public static String q5(Map<String, List<Product>> companyProductMap) {
        return companyProductMap.values()
                .stream()
                .max(Comparator.comparing(List::size))
                .get()
                .iterator()
                .next()
                .getCompany();

    }

    /**
     * QUESTION 6: Fill the lists (the value) of the map above. Hint: Use a
     * nested stream (one stream of productList and then a separate stream for
     * each list of each product).
     *
     * @param chemicalProductMap
     */
    public static void q6(Map<String, List<Product>> chemicalProductMap) {
        productList.stream()
                .forEach(x -> {
                    x.getChemicals().stream()
                            .forEach(y -> {
                                if (!chemicalProductMap.containsKey(y)) {
                                    List<Product> products = new ArrayList<>();
                                    products.add(x);
                                    chemicalProductMap.put(y, products);
                                } else {
                                    chemicalProductMap.get(y).add(x);
                                }
                            });
                });
    }

    /**
     * QUESTION 7: Which chemical appears in the most products? Hint: use
     * max(Comparator) again. Define your comparator to compare chemical names
     * based on the size of the list of products.
     *
     * @return String
     */
    public static String q7() {
        return chemicalProductMap.entrySet()
                .stream()
                .max(Comparator.comparing(x -> x.getValue().size()))
                .get()
                .getKey();

    }

    /**
     * Bonus Question Which product contains the most chemicals ?
     *
     * @return String
     */
    public static String bonusq() {
        return productList.stream()
                .max(Comparator.comparingInt(Product::getNumberOfChemicals))
                .get()
                .getName();
    }

    public static List<Product> createList() {
        String line = "";
        String fileName = "ChemicalData.csv";
        Map<Integer, Product> productMapByHash = new HashMap<Integer, Product>();

        try (Scanner fileScan = new Scanner(
                new FileReader(new File(fileName)))) {
            line = fileScan.nextLine(); // column headers        

            while (fileScan.hasNext()) {
                line = fileScan.nextLine();
                Scanner lineScan = new Scanner(line);
                lineScan.useDelimiter(",");

                String name = lineScan.next();
                String colorScentFlavor = lineScan.next();
                String company = lineScan.next();
                String brand = lineScan.next();
                String categoryString = lineScan.next();
                String chemicalName = lineScan.next();

                Category category = null;
                Category[] categories = Category.values();
                for (Category categoryOption : categories) {
                    if (categoryString.equalsIgnoreCase(categoryOption.getDescription())) {
                        category = categoryOption;
                    }
                }

                Product newProduct = new Product(name, company, brand, colorScentFlavor, category);

                int productHash = newProduct.hashCode();
                if (productMapByHash.containsKey(productHash)) {
                    Product existingProduct = productMapByHash.get(productHash);
                    existingProduct.addChemical(chemicalName);
                } else {
                    newProduct.addChemical(chemicalName);
                    productMapByHash.put(newProduct.hashCode(), newProduct);
                }

            }
        } catch (IOException ex) {
            System.out.println(line);
            ex.printStackTrace();
        }
        List<Product> list = new ArrayList<Product>(productMapByHash.values());
        return list;
    }

}
