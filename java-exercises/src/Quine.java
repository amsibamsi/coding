public class Quine {
static String[] s = {
"public class Quine {",
"static String[] s = {",
"};",
"public static void main(String[] args) {",
"System.out.println(s[0]);",
"System.out.println(s[1]);",
"for (int i=0;i<10;i++){System.out.println((char)34+s[i]+(char)34+(char)44);}",
"for (int i=2;i<10;i++){System.out.println(s[i]);}",
"System.out.println(s[9]);",
"}",
};
public static void main(String[] args) {
System.out.println(s[0]);
System.out.println(s[1]);
for (int i=0;i<10;i++){System.out.println((char)34+s[i]+(char)34+(char)44);}
for (int i=2;i<10;i++){System.out.println(s[i]);}
System.out.println(s[9]);
}
}
