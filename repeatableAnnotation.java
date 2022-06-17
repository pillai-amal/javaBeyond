import java.lang.annotation.*;
import java.lang.reflect.Method;

@Repeatable(value=ChangeDetails.class)
@Retention(RetentionPolicy.RUNTIME)
@interface ChangeDetail {
	String authorName();

	String methodName();

	int id() default 1004;
}

@Retention(RetentionPolicy.RUNTIME)
@interface ChangeDetails {
	ChangeDetail[] value();
}

abstract class Phone {
	String mobileName;

	public String getMobileName() {
		return mobileName;
	}

	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}
	
	abstract double price();

}

@ChangeDetail(authorName = "Sam" , methodName = "price")
@ChangeDetail(authorName = "Jack" , methodName = "calc" , id=1005)
class Nokia extends Phone {

	@Override
	double price() {
		return 15000;
	}
	
	double calc() {
		return 13000;
	}

}


class CustomAnnotationDemo {
	public static void main(String[] args) throws Exception {
	    Phone ph = new Nokia();
		Class c = ph.getClass();
		
		// Fetches the list of annotations of the class, Nokia
		Annotation annotations[] = c.getDeclaredAnnotations();
		for (Annotation annotation : annotations) {
			// Print the name of the annotation
			System.out.println(annotation.annotationType().getName());
			ph.setMobileName("Nokia 9");
			System.out.println("Mobile Name : " + ph.getMobileName());
			ChangeDetail[] chArray = Nokia.class.getDeclaredAnnotationsByType(ChangeDetail.class);
			for (ChangeDetail ch : chArray) {
				System.out.println("Author Name " +ch.authorName());
				System.out.println("Method Name " +ch.methodName());
				System.out.println("ID " +ch.id());
			}
		}
	}
}