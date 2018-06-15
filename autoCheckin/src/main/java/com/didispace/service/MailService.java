package com.didispace.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.didispace.dao.MailRecordRepository;
import com.didispace.entity.MailReciever;
import com.didispace.entity.MailRecord;

@Service
public class MailService {

	@Autowired
	private MailRecordRepository mailRecordRepository;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	protected TemplateEngine thymeleaf;

	/**
	 * 
	 * @param to
	 *            收件人
	 * @param subject
	 *            主题
	 * @param from
	 *            寄件人
	 * @param url
	 *            路径
	 * @param exception
	 *            错误信息
	 * @return
	 * @throws MessagingException
	 */
	public void emailException(String from, String to, String subject, String url, Exception exception)
			throws MessagingException {
		MimeMessage mailMessage = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);
		Context con = new Context();
		con.setVariable("url", url);
		con.setVariable("exception", exception);
		String emailtext = thymeleaf.process("error", con);
		// 设置收件人，寄件人 用数组发送多个邮件
		messageHelper.setTo(to);
		messageHelper.setFrom(from);
		messageHelper.setSubject(subject);
		// true 表示启动HTML格式的邮件
		messageHelper.setText(emailtext, true);
		// 发送邮件
		mailSender.send(mailMessage);
	}

	public void mailLog(SimpleMailMessage message, List<MailReciever> mailRecievers) {
		mailSender.send(message);
		MailRecord mailRecord = new MailRecord();
		if (message.getText().length() > 250)
			message.setText(message.getText().substring(249));
		mailRecord.setContent(message.getText());
		mailRecord.setTitle(message.getSubject());
		String name = "";
		for (MailReciever mailReciever : mailRecievers) {
			name += mailReciever.getName() + ", ";
		}
		name = name.substring(name.length() - 2);
		mailRecord.setName(name);
		mailRecordRepository.save(mailRecord);
		System.out.println("邮件已经发送和记录");
	}

	public void mailLog(SimpleMailMessage message, String mailReciever) {
		mailSender.send(message);
		MailRecord mailRecord = new MailRecord();
		if (message.getText().length() > 250)
			message.setText(message.getText().substring(249));
		mailRecord.setContent(message.getText());
		mailRecord.setTitle(message.getSubject());

		mailRecord.setName(mailReciever);
		mailRecordRepository.save(mailRecord);
		System.out.println("邮件已经发送和记录");
	}
}
