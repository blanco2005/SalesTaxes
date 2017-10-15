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
		final Item item = buildItemFromString(scanner.nextLine());
		result.add(item);
		scanner.close();
		return result;
	}

	private Item buildItemFromString(final String line) {

		final Scanner scanner = new Scanner(line);
		final int quantiy = scanner.nextInt();
		boolean imported = false;
		final StringBuilder sbDescription = new StringBuilder();
		parse: while (scanner.hasNext()) {
			final String nextToken = scanner.next();
			switch (nextToken) {
			case "imported":
				imported = true;
				sbDescription.append(nextToken);
				break;
			case "at":
				break parse;
			default:
				sbDescription.append(nextToken);
			}
		}
		final BigDecimal shelfPrice = new BigDecimal(scanner.next());
		final ItemType type = parseType(sbDescription.toString());
		scanner.close();
		return new Item(sbDescription.toString(), shelfPrice, type, imported);
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
