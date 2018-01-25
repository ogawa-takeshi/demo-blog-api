package com.example.blog.entry.event;

import am.ik.blog.entry.EntryId;
import com.example.blog.entry.EntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EntryDeleteEventListener {
	private final Logger log = LoggerFactory.getLogger(EntryDeleteEventListener.class);
	private final EntryRepository entryRepository;
	private final EventNotifyer eventNotifyer;

	public EntryDeleteEventListener(EntryRepository entryRepository,
			EventNotifyer eventNotifyer) {
		this.entryRepository = entryRepository;
		this.eventNotifyer = eventNotifyer;
	}

	@EventListener
	public void onUpdate(EntryDeleteEvent event) {
		EntryId entryId = event.getEntryId();
		log.info("Delete {}", entryId);
		this.entryRepository.delete(entryId);
		this.eventNotifyer.notify(new EntryDeleteEvent(entryId));
	}
}
