---
nav_order: 5
title: 5. მემკვიდრეობითობა
parent: გვანცა
grand_parent: სემინარები
---

# 05

## ინტერფეისები, აბსტრაქტული კლასები

- შექმენით `Device` ინტერფეისი `on` და `off` მეთოდებით.
- შექმენით `Device` ინტერფეისის მემკვიდრე `Electronic` ინტერფეისი `plugin` მეთოდით.
- შექმენით `Phone` აბსტრაქტული კლასი აბსტრაქტული `makeCall(String number)` და არააბსრაქტული `testCall(String number)` მეთოდებით.
- `Phone` კლასი უნდა აკეთებდეს `Electronic` ინტერფეისის იმპლემენტაციას.
- შექმენით `Phone` კლასის მემკვიდრე `Mobile` კლასი და აღწერეთ მხოლოდ ერთი, ნებისმიერი მეთოდი.
- გადააკეთეთ `Electronic` ინტერფეისი აბსტრაქტულ კლასად.

[კოდი](https://github.com/Freeuni-Lekva/oop-2021/tree/main/Content/Seminars/Gvantsa/05/Interfaces)

- აღწერეთ `Parent` მშობელი და `Child` შვილობილი კლასები.
- აღწერეთ `go` მეთოდი, რომელიც არაფერს აბრუნებს და კონსოლში ბეჭდავს `go method in Parent class`.
- გადაფარეთ `go` მეთოდი `Child` კლასში, რომელიც დაბეჭდავს `go method in Child class`.
- აღწერეთ `Test` კლასი
- აღწერეთ ორი სტატიკური `invoke` მეთოდი `Test` კლასში. პირველს პარამეტრად ჰქონდეს `Parent` ხოლო მეორეს `Child` ტიპი.
- ორივე `invoke` მეთოდში გამოიძახეთ `go` მეთოდი გადმოცემული პარამეტრიდან
- აღწერეთ `Test` კლასში `main` მეთოდი და შექმენით:
  - `Parent` კლასის ობიექტი `Parent` კლასის ტიპის მითითებით
  - `Child` კლასის ობიექტი `Child` კლასის ტიპის მითითებით
  - `Parent` კლასის ობიექტი `Child` კლასის ტიპის მითითებით
  - `Child` კლასის ობიექტი `Parent` კლასის ტიპის მითითებით
  - გადაეცით ყველა მითითება `invoke` მეთოდს ცალცალკე და დააკვირდით რა დაიბეჭდება.

[კოდი](https://github.com/Freeuni-Lekva/oop-2021/tree/main/Content/Seminars/Gvantsa/05/Inheritance)
