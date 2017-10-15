package ch.lastminute.parser;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import ch.lastminute.item.Item;
import ch.lastminute.item.ItemType;

public class Parser {

	public List<Item> parse(final String input) {
		if (input.isEmpty()) {
			return new LinkedList<>();
		}
		final List<Item> result = new LinkedList<>();
		final Scanner scanner = new Scanner(input);
		while (scanner.hasNextLine()) {
			final List<Item> items = buildItemFromString(scanner.nextLine());
			result.addAll(items);
		}
		scanner.close();
		return result;
	}

	private List<Item> buildItemFromString(final String line) {

		final Scanner scanner = new Scanner(line);
		final int quantiy = scanner.nextInt();
		boolean imported = false;
		final StringBuilder sbDescription = new StringBuilder();
		parse: while (scanner.hasNext()) {
			final String nextToken = scanner.next();
			switch (nextToken) {
			case "imported":
				imported = true;
				sbDescription.append(nextToken + " ");
				break;
			case "at":
				break parse;
			default:
				sbDescription.append(nextToken + " ");
			}
		}
		/** Remove extra space. **/
		sbDescription.deleteCharAt(sbDescription.length() - 1);
		final BigDecimal shelfPrice = new BigDecimal(scanner.next());
		final ItemType type = parseType(sbDescription.toString());
		scanner.close();
		final List<Item> result = new LinkedList<>();
		for (int i = 1; i <= quantiy; i++) {
			result.add(new Item(sbDescription.toString(), shelfPrice, type, imported));
		}
		return result;
	}

	private ItemType parseType(final String description) {
		if (description.contains("chocolate")) {
			return ItemType.FOOD;
		}
		if (description.contains("book")) {
			return ItemType.BOOK;
		}
		if (description.contains("pills")) {
			return ItemType.MEDICAL;
		}
		return ItemType.OTHER;
	}

}
