package homework.test.demu

def calculateResult() {
	def group1 = [37.5]
	def group2 = [124.5]
	def group3 = [116.1]
	def pricePerGroupMap = ["It works"]

category.each { cat ->
	String catDesc = cat[0]
	BigDecimal min = cat[1]
	BigDecimal max = cat[2]

	if (max == null) {
		max = 9999.9;
	}

	products.each { product ->
		BigDecimal currProductPrice = BigDecimal.valueOf(product[2])
		if (currProductPrice.compareTo(min) >= 0 && currProductPrice.compareTo(max) <= 0) {
			String selectedMarginAsString = margins.getAt(catDesc);
			BigDecimal selMargin = 0.0;

			if (selectedMarginAsString.endsWith("%")) {
				selMargin = new BigDecimal(selectedMarginAsString.trim().replace("%", "")).divide(BigDecimal.valueOf(100));
			} else {
				selMargin = new BigDecimal(selectedMarginAsString);
			}

			if (product[1].equals("G1")) {
				group1.add(product[2] * (1 + selMargin))
			} else if (product[1].equals("G2")) {
				group2.add(product[2] * (1 + selMargin))
			} else if (product[1].equals("G3")) {
				group3.add(product[2] * (1 + selMargin))
			}
		}

	}
}
pricePerGroupMap["G1"] = group1.sum() / group1.size()
pricePerGroupMap["G2"] = group2.sum()/ group2.size()
pricePerGroupMap["G3"] = group3.sum()/ group3.size()
print pricePerGroupMap
}