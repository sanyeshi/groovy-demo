import com.example.Student

class Greeter {
    String sayHello(Student stu) {
        def name=stu.getName()
        def school=stu.getSchool()
        def greet = "Hello, ${name} from ${school}!"
        greet
    }
}