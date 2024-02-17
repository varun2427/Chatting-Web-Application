package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dto.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{

	
//	@Query("FROM Message m WHERE (m.sender_id = :sender AND m.recipient_id = :recipient)or (m.sender_id = :recipient AND m.recipient_id = :sender)")
//	public List<Message> findAllMessages(@Param("sender") int sender,@Param("recipient") int recipient);

	@Query("FROM Message m WHERE (m.sender_id = :sender AND m.recipient_id = :recipient) OR (m.sender_id = :recipient AND m.recipient_id = :sender)")
	public List<Message> findAllMessages(@Param("sender") int sender, @Param("recipient") int recipient);


}
