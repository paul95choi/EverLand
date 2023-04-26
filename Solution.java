package Git;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Solution {
	public class OrderListClass {
		private int ticketGrade;
		private int totalPrice;
		private int ticketType;
		private int ticketCount;
		
		public OrderListClass(int ticketGrade, 
				int totalPrice, int ticketType, 
				int ticketCount) {
			this.ticketGrade = ticketGrade;
			this.totalPrice = totalPrice;
			this.ticketType = ticketType;
			this.ticketCount = ticketCount;
		}
		
		public int getTicketGrade() {
			return this.ticketGrade;
		}
		
		public int getTotalPrice() {
			return this.totalPrice;
		}
		
		public int getTicketType() {
			return this.ticketType;
		}
		
		public int getTicketCount() {
			return this.ticketCount;
		}
	}
	Scanner myInput = new Scanner(System.in);
	int ticketType, ticketTime, birthYear, ticketCount, ticketAdvantage;
	int totalPrice = 0, ticketGrade, age;
	final int CURRENT_YEAR = 2018;
	final int FREE_ONEDAY_ADULT = 55000, FREE_ONEDAY_YOUTH = 48000, FREE_ONEDAY_CHILD = 44000, FREE_ONEDAY_BABY = 14000;
	final int FREE_AFTER4_ADULT = 44000, FREE_AFTER4_YOUTH = 38000, FREE_AFTER4_CHILD = 34000, FREE_AFTER4_BABY = 14000;
	final int PARK_ONEDAY_ADULT = 52000, PARK_ONEDAY_YOUTH = 46000, PARK_ONEDAY_CHILD = 43000, PARK_ONEDAY_BABY = 14000;
	final int PARK_AFTER4_ADULT = 41000, PARK_AFTER4_YOUTH = 36000, PARK_AFTER4_CHILD = 33000, PARK_AFTER4_BABY = 14000;
	List<OrderListClass> data = new ArrayList<OrderListClass>();
	OrderListClass orderList = null; 

	public static void main(String[] args) {
		Scanner myInput = new Scanner(System.in);
		int repeat;
		Solution aa = new Solution();
		do {
			aa.input();
			aa.calc();
			aa.saveData();
			System.out.print("계속 하시겠습니까? (0:YES, 1:NO) : ");
			repeat = myInput.nextInt();
		}while( repeat == 0 );
		aa.output();
	}
	void saveData() {
		orderList = new OrderListClass(ticketGrade,
						totalPrice, ticketType, ticketCount);
		data.add(orderList);
	}
	void typeInput() {
		do {
			 System.out.print("권종을 입력하세요.\n1.종합이용권\n2.파크이용권 : ");
		     ticketType = myInput.nextInt();
		}while(  !(ticketType == 1 || ticketType == 2)  );
	}
	
	void typeTime() {
		do {
	        System.out.print("티켓 시간을 입력하세요.\n1.1일권\n2.After4 : ");
	        ticketTime = myInput.nextInt();
		}while( !(ticketTime == 1 || ticketTime == 2) );
	}
	
	void input() {
		// 입력
		typeInput();
		typeTime();
		do {
			System.out.print("생년(태어난 해)를 입력하세요. : ");
	        birthYear = myInput.nextInt();
		}while( birthYear < 1900   );
		do {
			System.out.print("몇 개를 주문하시겠습니까? : ");
	        ticketCount = myInput.nextInt();
		}while( ticketCount <= 0 || ticketCount > 10);
		do {
			System.out.print("우대사항을 입력하세요.\n1.없음\n2.장애인\n3.국가유공자 : ");
	        ticketAdvantage = myInput.nextInt();
		}while( !(ticketAdvantage == 1 || ticketAdvantage == 2 || ticketAdvantage == 3) );
	}
	
	
	int calcAge(int currentYear, int birthYear) {
		int result;
		result = currentYear - birthYear + 1;
		return result;
	}
	
	int checkGrade(int age) {
		int result;
		if(age <= 3) {
			result = 3;
        } else if(age <= 12 || age >= 65) {
        	result = 2;
        } else if(age <= 18) {
        	result = 1;
        } else {
        	result = 0;
        }
		return result;
	}
	int checkPrice(int type, int time, int grade) {
		int result = 0;
		if(type == 1) {
        	if(time == 1) {
        		switch(grade) {
        		case 0: 
        			result = FREE_ONEDAY_ADULT;
        			break;
        		case 1:
        			result = FREE_ONEDAY_YOUTH;
        			break;
        		case 2:
        			result = FREE_ONEDAY_CHILD;
        			break;
        		case 3: 
        			result = FREE_ONEDAY_BABY;
        			break;
        		}
        	} else if(time == 2) {
        		if(grade == 0) {
        			result = FREE_AFTER4_ADULT;
        		} else if(grade == 1) {
        			result = FREE_AFTER4_YOUTH;
        		} else if(grade == 2) {
        			result = FREE_AFTER4_CHILD;
        		} else if(grade == 3) {
        			result = FREE_AFTER4_BABY;
        		}
        	}
        } else if(type == 2){
        	if(time == 1) {
        		if(grade == 0) {
        			result = PARK_ONEDAY_ADULT;
        		} else if(grade == 1) {
        			result = PARK_ONEDAY_YOUTH;
        		} else if(grade == 2) {
        			result = PARK_ONEDAY_CHILD;
        		} else if(grade == 3) {
        			result = PARK_ONEDAY_BABY;
        		}
        	} else if(time == 2) {
        		if(grade == 0) {
        			result = PARK_AFTER4_ADULT;
        		} else if(grade == 1) {
        			result = PARK_AFTER4_YOUTH;
        		} else if(grade == 2) {
        			result = PARK_AFTER4_CHILD;
        		} else if(grade == 3) {
        			result = PARK_AFTER4_BABY;
        		}
        	}
        }
		return result;
	}
	void calc() {
		// 계산
        age = calcAge(CURRENT_YEAR, birthYear);
        ticketGrade = checkGrade(age);
        // 종합이용권
        totalPrice = checkPrice(ticketType, ticketTime, ticketGrade);
        
        // 갯수
        totalPrice = totalPrice * ticketCount;
        
        // 우대
        if(ticketAdvantage == 2) {
        	totalPrice = (int)( (double)totalPrice * 0.48 );
        } else if(ticketAdvantage == 3) {
        	totalPrice = totalPrice / 2;
        }
        
	}
	void output() {
		
		// 출력
        System.out.println("가격은 " + totalPrice + "원 입니다.\n감사합니다.");
        System.out.println("================롯데월드=================");
        
        for(OrderListClass order : data) {
        	ticketType = order.getTicketType();
        	ticketGrade = order.getTicketGrade();
        	totalPrice = order.getTotalPrice();
        	ticketCount = order.getTicketCount();
        	
			if(ticketType == 1) {
	        	System.out.print("종합이용권\t");
	        } else {
	        	System.out.print("파크이용권\t");
	        }
	        if(ticketGrade == 0) {
	        	System.out.print("어른 ");
	        } else if(ticketGrade == 1) {
	        	System.out.print("청소년 ");
	        } else if(ticketGrade == 2) {
	        	System.out.print("어린이 ");
	        } else if(ticketGrade == 3) {
	        	System.out.print("베이비 ");
	        }
	        System.out.println("X " + ticketCount + "\t " + totalPrice + "원");
		}
        
        System.out.println("=======================================");
	}

}
