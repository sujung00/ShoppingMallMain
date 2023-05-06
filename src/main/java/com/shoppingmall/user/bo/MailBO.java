package com.shoppingmall.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.shoppingmall.common.EncryptUtils;
import com.shoppingmall.user.model.Mail;

@Service
public class MailBO {
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private JavaMailSender mailSender;

	
	public Mail createMailAndChangePassword(String userEmail, String userName){
        String str = getTempPassword();
        Mail mail= new Mail();
        mail.setAddress(userEmail);
        mail.setTitle(userName+"님의 TREND 임시비밀번호 안내 이메일 입니다.");
        mail.setMessage("안녕하세요. TREND입니다.\n" + "임시비밀번호 안내 관련 이메일 입니다.\n" + "[" + userName + "]" +"님의 임시 비밀번호는 "
        + str + " 입니다.");
        updatePassword(str, userEmail);
        
        return mail;
    }
	
	// 임시 비밀번호로 업데이트
	public void updatePassword(String str, String userEmail){
        // 해싱된 비밀번호
		String pw = EncryptUtils.md5(str);
        int userId = userBO.getUserByEmail(userEmail).getId();
        
        userBO.updateUserPassword(userId, pw);
    }

	// 랜덤 함수로 임시 비밀번호 구문 만들기
    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
    
    public void mailSend(Mail mail){
    	SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getAddress());
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());
        message.setFrom("trend0505@naver.com");
        message.setReplyTo("TREND");
        mailSender.send(message);

    }
}
