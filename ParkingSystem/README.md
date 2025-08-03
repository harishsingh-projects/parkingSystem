# Smart Parking Lot System

## Overview

Smart Parking Lot System ek backend application hai jo urban parking lots ke liye design kiya gaya hai. Yeh system efficiently vehicle entry-exit management, parking spot allocation, aur fee calculation handle karta hai. System real-time availability update aur reservation feature bhi support karta hai, taaki users ko seamless parking experience mile.

---

## Features

- **Automatic Parking Spot Allocation:** Vehicles ke size ke hisaab se spot assign karta hai (Motorcycle, Car, Bus).
- **Vehicle Check-In and Check-Out:** Entry aur exit times track karta hai.
- **Parking Fee Calculation:** Parking duration aur vehicle type ke basis par fees calculate karta hai.
- **Real-Time Availability Update:** Spots ki availability update hoti hai jaise vehicles enter ya exit karte hain.
- **Reservation System:** Jab spot unavailable ho, vehicle reservation kar sakta hai aur spot free hone par notification milti hai.
- **Observer Pattern:** Notification service observers ko important events ke baare me update karta hai.
- **Strategy Pattern:** Flexible fee calculation ke liye implement kiya gaya hai.

---

## Architecture & Design

- **Object-Oriented Design:** Classes jaise Vehicle, ParkingSpot, Floor, ParkingTicket, Reservation.
- **SOLID Principles:** Code modular, extensible, aur maintainable hai.
- **Design Patterns:** Observer pattern for notifications, Strategy pattern for fee calculation.
- **Concurrency:** Basic synchronization to handle simultaneous vehicle entry/exit.
- **Scalable Model:** Multiple floors supported; multi-branch system future me add kiya ja sakta hai.

---

## Technologies Used

- Java 17+
- Java Collections Framework
- Design Patterns (Observer, Strategy)
- Java Time API (LocalDateTime)
- Standard logging (System.out / Observer notifications)

---

## Project Structure

src/main/java/org/parking/
├── factory/
│ ├── VehicleFactory.java
│ └── ParkingSpotFactory.java
├── model/
│ ├── Vehicle.java
│ ├── VehicleType.java
│ ├── ParkingSpot.java
│ ├── Floor.java
│ ├── ParkingTicket.java
│ └── Reservation.java
├── service/
│ └── ParkingLotManager.java
├── strategy/
│ ├── FeeStrategy.java
│ └── DefaultFeeStrategy.java
├── observer/
│ ├── Subject.java
│ ├── Observer.java
│ └── NotificationService.java
└── Main.java