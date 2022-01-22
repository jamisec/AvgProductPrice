package homework.test.demu

class Main {
	
			public static void main(String[] args) {
			ArrayList<Product> products = new ArrayList<Product>();
			products.add(new Product("A", "G1", 20.1));
			products.add(new Product("B", "G2", 98.4));
			products.add(new Product("C", "G1", 49.7));
			products.add(new Product("D", "G3", 35.8));
			products.add(new Product("E", "G3", 105.5));
			products.add(new Product("F", "G1", 55.2));
			products.add(new Product("G", "G1", 12.7));
			products.add(new Product("H", "G3", 88.6));
			products.add(new Product("I", "G1", 5.2));
			products.add(new Product("J", "G2", 72.4));
	
			ArrayList<Category> categories = new ArrayList<Category>();
			categories.add(new Category("C3", 50, 75));
			categories.add(new Category("C4", 75, 100));
			categories.add(new Category("C2", 25, 50));
			categories.add(new Category("C5", 100, null));
			categories.add(new Category("C1", 0, 25));
	
			ArrayList<Margin> margins = new ArrayList<Margin>();
			margins.add(new Margin("C1", "20%"));
			margins.add(new Margin("C2", "30%"));
			margins.add(new Margin("C3", "0.4"));
			margins.add(new Margin("C4", "50%"));
			margins.add(new Margin("C5", "0.6"));
	
			HashMap<String, Double> result = new HashMap<String, Double>();
			HashMap<String, Integer> occurrencies = new HashMap<String, Integer>();
	
			for (Product product : products) {
				for (Category category : categories) {
					if (product.getCost() >= category.getCostFrom() && product.getCost() < category.getCostTo()) {
						for (Margin margin : margins) {
							if (category.getCategory().equals(margin.getCategory())) {
								double price = product.getCost() * (1 + Double.valueOf(margin.getMargin()));
								String group = product.getGroup();
	
								if (result.containsKey(group)) {
									result.put(group, result.get(group) + price);
									occurrencies.put(group, occurrencies.get(group) + 1);
								} else {
									result.put(group, price);
									occurrencies.put(group, 1);
								}
								break;
							}
						}
						break;
					}
				}
			}
	
			// Calculate average Product price per Group
			for (String group : result.keySet())
				result.put(group, (double) Math.round((result.get(group) / occurrencies.get(group)) * 10) / 10);
	
			// Define expected result
			HashMap<String, Double> expectedResult = new HashMap<String, Double>();
			expectedResult.put("G1", 37.5);
			expectedResult.put("G2", 124.5);
			expectedResult.put("G3", 116.1);
	
			// Check result correctness
			if (result.equals(expectedResult))
				System.out.println("It works!");
			else
				System.out.println("It doesn't work");
	
		}
	
		static class Product {
			String product, group;
			double cost;
	
			Product(String product, String group, double cost) {
				this.product = product;
				this.group = group;
				this.cost = cost;
			}
	
			public String getProduct() {
				return this.product;
			}
	
			public String getGroup() {
				return this.group;
			}
	
			public double getCost() {
				return this.cost;
			}
		}
	
		static class Category {
			String category;
			Integer costFrom, costTo;
	
			Category(String category, Integer costFrom, Integer costTo) {
				this.category = category;
				this.costFrom = costFrom;
				this.costTo = (costTo == null) ? Integer.MAX_VALUE : costTo;
			}
	
			public String getCategory() {
				return this.category;
			}
	
			public Integer getCostFrom() {
				return this.costFrom;
			}
	
			public Integer getCostTo() {
				return this.costTo;
			}
		}
	
		static class Margin {
			String category, margin;
	
			Margin(String category, String margin) {
				this.category = category;
	
				if (margin.contains("%"))
					this.margin = String.valueOf((Double.valueOf(margin.replace("%", "")) / 100));
				else
					this.margin = margin;
			}
	
			public String getCategory() {
				return this.category;
			}
	
			public String getMargin() {
				return this.margin;
			}
		}
	}