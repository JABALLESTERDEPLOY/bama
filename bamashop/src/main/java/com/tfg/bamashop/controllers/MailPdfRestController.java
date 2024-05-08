package com.tfg.bamashop.controllers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.tfg.bamashop.entities.Pdf;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v0")
@CrossOrigin("*")
@Slf4j
@Tag(name = "MailPdfRestController", description = "MailPdfRestController API")
@SecurityRequirement(name = "bearerAuth")
public class MailPdfRestController {
	private final JavaMailSender javaMailSender;

	public MailPdfRestController(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Operation(summary = "Generate And Send PDF", operationId = "generateAndSendPdf", description = "Generate and send a PDF")
	@PostMapping("/send/{email}")
	public ResponseEntity<Object> generateAndSendPdf(@PathVariable String email, @RequestBody Pdf request)
			throws Exception {

		log.info("Init generateAndSendPdf - MailPdfRestController");

		String html = request.getHtml();
		String target = request.getTarget();
		String subject = "Invoice #" + request.getSubject();
		String body = request.getBody();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(html);
		renderer.layout();
		renderer.createPDF(outputStream);
		renderer.finishPDF();

		String fileName = "invoice#" + request.getSubject() + ".pdf";
		File pdfFile = File.createTempFile("temp", fileName);
		FileOutputStream fos = new FileOutputStream(pdfFile);
		fos.write(outputStream.toByteArray());
		fos.close();

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMultipart multipart = new MimeMultipart();
		MimeBodyPart textBodyPart = new MimeBodyPart();
		textBodyPart.setText(body);
		multipart.addBodyPart(textBodyPart);

		MimeBodyPart pdfBodyPart = new MimeBodyPart();
		FileDataSource source = new FileDataSource(pdfFile);
		pdfBodyPart.setDataHandler(new DataHandler(source));
		pdfBodyPart.setFileName(fileName);
		multipart.addBodyPart(pdfBodyPart);

		message.setContent(multipart);
		message.setSubject(subject);
		message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(target));
		javaMailSender.send(message);

		return ResponseEntity.ok("Invoice was sent successfully");
	}

}
