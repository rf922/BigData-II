# BigData-II
This is Part II of a two-part project originally assigned in Advanced Java Programming, taught by Jessica Masters. While Part I focused on text and CSV analysis using Java Streams, BigData-II dives deeper into data modeling and stream-based aggregation, using a large dataset of personal care products and their chemical compositions.


---

## 🧪 Project Overview

BigData-II performs stream-based analysis on a dataset of over **73,000 personal care products**, using `ChemicalData.csv`. Each product includes:

- Product name
- Company and brand
- Category (e.g., Makeup, Hair Care, Baby Products)
- Color/Scent/Flavor
- A set of associated **chemical ingredients**

Using Java 8+ Streams, the project extracts insights and answers key data science questions through elegant and performant functional programming techniques.

---

## 📁 File Structure

- **`ChemData/Product.java`**  
  Encapsulates product attributes and behavior, including chemical tracking.

  - **`ChemData/Category.java`**  
    Enum defining high-level product categories with descriptive labels.

    - **`ChemData/ProductBigDataQuestions.java`**  
      Core logic for:
        - Reading and parsing `ChemicalData.csv`
          - Populating product and chemical structures
            - Answering 7 analytical questions + 1 bonus
              - Outputting statistics to the console

              ---

## 🔍 Analytical Questions Answered

1. **Total number of chemicals** used across all products  
2. **Number of companies** represented  
3. Count of products with **4 or more chemicals**  
4. Mapping of **companies to product lists**  
5. Identification of the **company with the most products**  
6. Mapping of **chemicals to the products** they appear in  
7. Determination of the **most commonly used chemical**  
8. *(Bonus)* Which **product contains the most chemicals**

---

## 🧠 Sample Insights

- **Titanium Dioxide** appears in over **67,000 products**
- **LOreal USA** has the largest product count in the dataset
- Some products contain **20+ unique chemical ingredients**
- The dataset spans **549 companies** and diverse product categories

---

## 💡 Learning Highlights

- Proficient use of Java Streams, collectors, and comparators
- Real-world CSV parsing with object aggregation
- Advanced map-building using nested streams and grouping logic
- Use of `equals` and `hashCode` for de-duplication
- Enum-based category modeling for clarity and type safety

---

## 🔧 Requirements

- Java 8 or later
- CSV data file: `ChemicalData.csv` in the project root

To run:

```bash
javac ChemData/*.java
java ChemData.ProductBigDataQuestions

