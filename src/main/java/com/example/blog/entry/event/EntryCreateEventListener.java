package com.example.blog.entry.event;

import am.ik.blog.entry.Entry;
import com.example.blog.entry.EntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EntryCreateEventListener {
	private final Logger log = LoggerFactory.getLogger(EntryCreateEventListener.class);
	private final EntryRepository entryRepository;
	private final EventNotifyer eventNotifyer;

	public EntryCreateEventListener(EntryRepository entryRepository, EventNotifyer eventNotifyer) {
		this.entryRepository = entryRepository;
		this.eventNotifyer = eventNotifyer;
	}

	@EventListener
	public void onUpdate(EntryCreateEvent event) {
		Entry entry = event.getEntry();
		log.info("Create {}", entry.getEntryId());
		this.entryRepository.create(entry);
		this.eventNotifyer.notify(event);
	}
}
