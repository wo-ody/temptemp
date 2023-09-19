package dto;

import java.time.LocalDateTime;

public class RegisterDto {
	private int userId;
	private String userName;
	private String userEmail;
	private String userPassword;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public RegisterDto() {}
	
	
	
	public RegisterDto(String userName, String userEmail, String userPassword) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}
	public RegisterDto(String userName, String userEmail, String userPassword, LocalDateTime createdAt) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.createdAt = createdAt;
	}
	public RegisterDto(int userId, String userName, String userEmail, String userPassword, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	@Override
	public String toString() {
		return "RegisterDto [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail
				+ ", userPassword=" + userPassword + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	
}
