# Online Store Management System

This project is a console-based Java application that simulates an online store system.

## Features

- User account creation with validation (username & password rules)
- Login system for customers and admins
- Admin can:
  - Add items
  - Remove items
  - Update stock
  - View all items
- Customer can:
  - View items
  - Add items to cart
  - Remove items from cart
  - Checkout and place orders

## OOP Concepts Used

- Abstract Classes (User, Item)
- Inheritance (Customer, Employee, Admin, ElectronicItem, GroceryItem)
- Interface (searchable)
- Polymorphism (method overriding like getRole, calculateDiscount)
- Composition (Customer has ShoppingCart)
- Aggregation (Store has Items and Users)
- Recursion (count items in cart)

