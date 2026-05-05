# 🌐 Pick up! - International Logistics Peer-to-Peer Platform

**Pick up!** is a full-stack web application designed to solve the pain points of high international shipping costs and urgent delivery needs, specifically targeting the Germany-China route. It facilitates a peer-to-peer marketplace where users can offer or request luggage space for documents, snacks, and small items.

## ✨ Key Features

*   **Dual-Role Marketplace**: Users can seamlessly switch between "Need Help" (Seekers) and "Provide Help" (Carriers) roles.
*   **Integrated Real-time Chat**: Interactive conversation mode featuring message grouping, intuitive UI (sender/receiver bubbles), and unread notifications (blue-dot badges).
*   **Localized User Experience**: 
    *   **Trilingual Support**: Full internationalization (i18n) for **Chinese, English, and German**.
    *   **Kleinanzeigen Style Integration**: User trust badges (e.g., "TOP Zufriedenheit") inspired by German marketplace standards to enhance trust.
*   **Dynamic Profile Management**: Editable user profiles with real-time updates for usernames and personal bios.
*   **Responsive Task Tracking**: Dynamic status management to easily mark posts as "Available" or "Booked".

## 🚀 Tech Stack

*   **Backend**: Java 17, Spring Boot, Spring Data JPA, RESTful APIs.
*   **Database**: H2 (In-memory/Persistent) for rapid development and data testing.
*   **Frontend**: Vanilla JavaScript (ES6+), HTML5, CSS3.
*   **Version Control**: Git & GitHub.

## 📸 Preview & Interaction Logic

The platform implements a sophisticated chat logic where:
1. **Context-Aware Messaging**: Private messages are automatically linked to the original flight/task post.
2. **Dynamic UI Rendering**: Chat bubbles are color-coded to provide a familiar, modern messaging experience.
3. **Data Consistency**: Built on a relational database schema ensuring robust links between Users, Posts, and Messages.

## 🛠️ Local Setup & Run

1.  **Clone the repository**:
    ```bash
    git clone [https://github.com/wkat7881-ctrl/pickup-express.git](https://github.com/wkat7881-ctrl/pickup-express.git)
