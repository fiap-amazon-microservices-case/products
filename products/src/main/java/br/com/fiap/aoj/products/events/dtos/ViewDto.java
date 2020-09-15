package br.com.fiap.aoj.products.events.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class ViewDto {

	public static final ViewDto of(final String productId, final String categoryId){
		final ViewDto viewDto = new ViewDto();
		final ItemDto itemDto = new ItemDto();
		itemDto.setProductId(productId);
		itemDto.setCategoryId(categoryId);

		viewDto.setItem(itemDto);
		return viewDto;
	}

	private UUID id = UUID.randomUUID();

	private LocalDateTime viewedAt = LocalDateTime.now();

	private ItemDto item;

	public UUID getId() {
		return id;
	}

	public LocalDateTime getViewedAt() {
		return viewedAt;
	}

	public ItemDto getItem() {
		return item;
	}

	public void setItem(ItemDto item) {
		this.item = item;
	}
}