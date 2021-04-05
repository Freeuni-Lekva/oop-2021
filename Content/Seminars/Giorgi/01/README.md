---
nav_order: 1
title: 1. ჯავას აპლიკაციების კომპილაცია და გაშვება
parent: გიორგი
grand_parent: სემინარები
---

# 01

Java აპლიკაციების Command Line-ის გამოყენებით კომპილაცია და გაშვება.

```shell
# *.java ფაილის დაკომპილირება და გაშვება
> java Main.java
Hello OOP-2021!!

# მხოლოდ კომპილაცია
> javac Main.java
> ls
Main.class	Main.java

# დაკომპილირებული კლას ფაილის გაშვება (*.java ფაილი აღარ არის აუცილებელი)
> java Main
Hello OOP-2021!!
```

Java აპლიკაციების/ბიბლიოთეკების დისტრიბუცია ხდება JAR ფაილების გამოყენებით, რომელიც იგივე ZIP არქივია. მასში შეიძლება იყოს META-INF დირექტორია რომელშიც ინახება მეტა ინფორმაცია აპლიკაციის/ბიბლიოთეკის შესახებ.

```shell
# jar არქივის შექმნა
> jar cf app.jar Main.java
> ls
Main.class	Main.java	app.jar

# jar აპლიკაციის გაშვება
> java -jar app.jar
no main manifest attribute, in app.jar
```

ბოლო ბრძანება შეცდომას აბრუნებს რადგან მანიფესტში არ მიგვითითებია მთავარ/საწყისი კლასის სახელი რომელიც უნდა გაეშვას.
ეს ინფორმაცია უნდა ინახებოდეს არქივში მყოფ META-INF/MANIFEST.MF ფაილში.

```shell
> mkdir tmp
> cp app.jar tmp
> cd tmp
> unzip app.jar
> find . -print
.
./app.jar
./META-INF
./META-INF/MANIFEST.MF
./Main.java
> cat META-INF/MANIFEST.MF
Manifest-Version: 1.0
Created-By: 13-ea (Oracle Corporation)
```

```shell
> echo "Manifest-Version: 1.0" > Hello.MF
> echo "Main-Class: Main" >> Hello.MF
> cat Hello.MF
Manifest-Version: 1.0
Main-Class: Main
> jar cmf Hello.MF app.jar Main.java
> java -jar app.jar
Error: Could not find or load main class jar
Caused by: java.lang.ClassNotFoundException: jar
# JAR ფაილში source კოდთან ერთად მისი დაკომპილირებული ვარიანტიც უნდა მოგვეთავსებინა
> jar cmf Hello.MF app.jar Main.java Main.class
> java -jar app.jar
Hello OOP-2021!!
# შეგვიძლია source კოდი არ შევიტანოთ არქივში
> jar cmf Hello.MF app.jar Main.class
> java -jar app.jar
```

```shell
> cd hello
> javac Hello.java
> cd ../
> jar cf hello.jar hello/Hello.*
# ბიბლიოთეკების მითითება შეიძლება cp იგივე classpath არგუმენტის გამოყენებით
> java -cp hello.jar MainV2.java
```
