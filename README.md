# Bidding Web Application 🛍️

A reverse-marketplace platform where buyers publish what they need and sellers compete by bidding to fulfil those requests. The application delivers a complete Java stack with REST APIs, Hibernate ORM, MySQL persistence, email notifications, and a JSP-based front end.

---

## Table of Contents

- [Overview](#overview)
- [User Journey](#user-journey)
- [Feature Highlights](#feature-highlights)
- [Architecture & Technology Stack](#architecture--technology-stack)
- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Database Setup](#database-setup)
- [Application Configuration](#application-configuration)
- [Build & Run](#build--run)
- [API Reference](#api-reference)
- [Project Structure](#project-structure)
- [Security & Compliance](#security--compliance)
- [Performance Notes](#performance-notes)
- [Troubleshooting](#troubleshooting)
- [Support & Contributions](#support--contributions)
- [License](#license)

---

## Overview

The **Bidding Web Application** flips the conventional marketplace model. Instead of sellers listing inventory, buyers post detailed requests for items, services, or expertise, and sellers respond with competitive bids. The winning bid is accepted through a cart and order workflow, keeping both parties informed through automated emails and dashboards.

### Real-World Scenarios

- **Procurement Teams** source hardware/software quotes from multiple vendors quickly.
- **Services Exchange** lets individuals request design, tutoring, or consulting work.
- **Community Marketplace** helps neighbours share tools or resources at competitive rates.
- **Knowledge Marketplace** pairs learners with subject-matter experts for on-demand help.

---

## User Journey

1. **Register** – Create an account with personal details and secure credentials.
2. **Authenticate** – Log in with email/password; failed attempts are tracked.
3. **Post Items** – Describe requirements (goods, services, knowledge, or funds).
4. **Receive Bids** – Monitor offers with prices and bidder information.
5. **Select & Checkout** – Add chosen bids to the cart, adjust quantity, and confirm.
6. **Notifications** – Buyer and seller receive confirmation emails with order details.
7. **History** – Users review past bids, accepted offers, and fulfilled orders.

---

## Feature Highlights

### User Management
- Registration with validation and unique username/email enforcement.
- Secure login with password hashing (plug in your hashing strategy for production).
- Account lockout after configurable failed login attempts.
- Profile editing and last-login timestamp tracking.

### Marketplace Operations
- Create item requests with rich descriptions and automatic user association.
- Advanced search with keyword filtering and sortable tables.
- Bid management for both buyers (incoming) and sellers (outgoing).
- Cart and checkout flow with order persistence and status tracking.

### Platform Services
- RESTful endpoints for every core use case.
- Memcached integration for hot data caching.
- GZip compression filter for faster responses.
- Email notifications via configurable SMTP server.

---

## Architecture & Technology Stack

```
Browser (HTML/CSS/JS) ─┐
                       ▼
                  JSP Views
                       ▼
                REST Controllers (Jersey)
                       ▼
              Business Logic / Managers
                       ▼
                  Hibernate ORM Layer
                       ▼
                    MySQL Database
                       ▼
        ┌────────────┬──────────────┬──────────────┐
        ▼            ▼              ▼              ▼
    Memcached   SMTP Service   Logging/Monitoring  External Integrations
```

| Layer | Technologies |
|-------|--------------|
| Presentation | JSP, HTML5, CSS3, JavaScript, jQuery |
| Service | Java 21, JAX-RS (Jersey 1.19.4), Servlet API 3.1 |
| Business | Hibernate 5.6.15, JavaMail, custom managers |
| Persistence | MySQL 8.0+, JPA annotations, HQL |
| Build & Deploy | Maven 3.9+, Apache Tomcat 9+, Docker (optional) |

---

## Prerequisites

| Requirement | Version | Purpose |
|-------------|---------|---------|
| Java Development Kit | 21 | Compile and run the application |
| Apache Maven | 3.9.x | Build automation and dependency management |
| MySQL Server | 8.0.x | Application database |
| Apache Tomcat | 9.0.x | Servlet container for deployment |
| Git | Latest | Clone and manage source control |
| Memcached (optional) | Latest | Response caching |

**System Specs**: 4 GB RAM (8 GB recommended), dual-core CPU, 2 GB free disk space, and a modern browser (Chrome/Firefox/Safari/Edge current releases).

---

## Quick Start

```bash
# Clone the repository
cd ~/Projects
git clone https://github.com/mohanakrishnavh/bidding-web-application.git
cd bidding-web-application/RestHibernate

# Verify tooling
java -version
mvn -version
mysql --version

# Build
mvn clean install

# Deploy to Tomcat (adjust TOMCAT_HOME as needed)
cp target/RestHibernate-0.0.1-SNAPSHOT.war $TOMCAT_HOME/webapps/bidding.war

# Start Tomcat
$TOMCAT_HOME/bin/startup.sh     # Linux/macOS
# Windows: "%TOMCAT_HOME%\bin\startup.bat"
```

Navigate to `http://localhost:8080/bidding/`.

---

## Database Setup

### 1. Start MySQL
- macOS (Homebrew): `brew services start mysql`
- Linux (systemd): `sudo systemctl start mysql`
- Windows: start the **MySQL80** service or use MySQL Workbench.

### 2. Provision Schema

```sql
CREATE DATABASE bidding_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE USER 'bidding_user'@'localhost'
  IDENTIFIED BY 'ChangeMe@2025';
GRANT ALL PRIVILEGES ON bidding_db.* TO 'bidding_user'@'localhost';
FLUSH PRIVILEGES;
```

> Replace the password with a strong secret unique to your installation.

### 3. Create Tables

```sql
USE bidding_db;

CREATE TABLE user (
  u_id INT AUTO_INCREMENT PRIMARY KEY,
  uname VARCHAR(100) NOT NULL UNIQUE,
  fname VARCHAR(100) NOT NULL,
  lname VARCHAR(100) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  lastlogintime TIMESTAMP NULL,
  failedLoginCount INT DEFAULT 0,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB;

CREATE TABLE items (
  p_id INT AUTO_INCREMENT PRIMARY KEY,
  u_id INT NOT NULL,
  p_name VARCHAR(255) NOT NULL,
  p_desc TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (u_id) REFERENCES user(u_id) ON DELETE CASCADE,
  FULLTEXT idx_search (p_name, p_desc)
) ENGINE=InnoDB;

CREATE TABLE bids (
  bid_id INT AUTO_INCREMENT PRIMARY KEY,
  p_id INT NOT NULL,
  u_id INT NOT NULL,
  bid_price DECIMAL(10,2) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (p_id) REFERENCES items(p_id) ON DELETE CASCADE,
  FOREIGN KEY (u_id) REFERENCES user(u_id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE orders (
  order_id INT AUTO_INCREMENT PRIMARY KEY,
  u_id INT NOT NULL,
  sell_id INT NOT NULL,
  p_id INT NOT NULL,
  order_price DECIMAL(10,2) NOT NULL,
  quantity INT DEFAULT 1,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (u_id) REFERENCES user(u_id) ON DELETE CASCADE,
  FOREIGN KEY (sell_id) REFERENCES user(u_id) ON DELETE CASCADE,
  FOREIGN KEY (p_id) REFERENCES items(p_id) ON DELETE CASCADE
) ENGINE=InnoDB;
```

### 4. Optional Seed Data

```sql
INSERT INTO user (uname, fname, lname, email, password)
VALUES ('demo_buyer', 'Demo', 'Buyer', 'buyer@example.com', 'hashed_password');

INSERT INTO items (u_id, p_name, p_desc)
VALUES (1, 'MacBook Pro 16-inch', 'Looking for M3 chip, 16 GB RAM, delivery within 2 weeks.');
```

---

## Application Configuration

### Hibernate (`src/main/resources/hibernate.cfg.xml`)

```xml
<property name="hibernate.connection.url">
  jdbc:mysql://localhost:3306/bidding_db?useSSL=false&amp;serverTimezone=UTC
</property>
<property name="hibernate.connection.username">bidding_user</property>
<property name="hibernate.connection.password">ChangeMe@2025</property>
<property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>
<property name="hibernate.hbm2ddl.auto">update</property>
<property name="hibernate.show_sql">false</property>
```

Set `hibernate.hbm2ddl.auto` to `validate` in production environments.

### Email (`WebContent/WEB-INF/web.xml`)

```xml
<context-param>
  <param-name>host</param-name>
  <param-value>smtp.gmail.com</param-value>
</context-param>
<context-param>
  <param-name>port</param-name>
  <param-value>587</param-value>
</context-param>
<context-param>
  <param-name>user</param-name>
  <param-value>notifications@example.com</param-value>
</context-param>
<context-param>
  <param-name>pass</param-name>
  <param-value>app-specific-password</param-value>
</context-param>
```

- Use provider-specific configurations for Outlook, Yahoo, etc.
- Gmail requires 2FA and an App Password for SMTP access.

### Memcached
- Default host: `localhost`
- Default port: `11211`
- Start the service via `brew services start memcached` (macOS) or `sudo systemctl start memcached` (Linux).

---

## Build & Run

### Maven

```bash
mvn clean install              # Compile, test, and package the WAR
```

Artifacts are generated inside `target/` including `RestHibernate-0.0.1-SNAPSHOT.war`.

### Tomcat Deployment

1. Copy the WAR to `${TOMCAT_HOME}/webapps/bidding.war`.
2. Start Tomcat (`startup.sh`/`startup.bat`).
3. Follow logs to confirm deployment: `tail -f ${TOMCAT_HOME}/logs/catalina.out`.
4. Browse to `http://localhost:8080/bidding/`.

### IDE Workflow
- **IntelliJ IDEA**: Import Maven project → Add Tomcat local configuration → Deploy artifact → Run.
- **Eclipse**: Import as *Existing Maven Project* → Add to configured Tomcat server → Run on Server.

### Docker (Optional)

```dockerfile
FROM tomcat:9.0-jdk21
RUN rm -rf /usr/local/tomcat/webapps/*
COPY target/RestHibernate-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/bidding.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
```

```bash
docker build -t bidding-app .
docker run -p 8080:8080 --name bidding bidding-app
```

---

## API Reference

Base URL: `http://localhost:8080/bidding/rest`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/login` | Authenticate user |
| POST | `/register` | Create new account |
| GET | `/user/{id}` | Retrieve profile |
| PUT | `/user/update` | Update user details |
| POST | `/item/create` | Post new item request |
| GET | `/items` | List all items |
| GET | `/search?q={term}` | Search items |
| POST | `/bid/create` | Submit a bid |
| GET | `/bids/user/{id}` | View bids placed by a user |
| GET | `/bids/item/{id}` | View bids received on an item |
| POST | `/order/create` | Finalize order from accepted bid |
| GET | `/orders/user/{id}` | Retrieve order history |

Responses are JSON formatted. Add authentication middleware (tokens/JWT) if exposing externally.

---

## Project Structure

```
bidding-web-application/
├── README.md
├── RestHibernate/
│   ├── pom.xml
│   ├── src/main/java/com/
│   │   ├── data/hibernate/     # Entities, DAOs, session managers
│   │   ├── services/rest/      # REST controllers
│   │   └── wpl/                # Commons and JSON utilities
│   ├── src/main/resources/
│   │   ├── hibernate.cfg.xml
│   │   └── orders.hbm.xml
│   ├── WebContent/
│   │   ├── WEB-INF/web.xml
│   │   ├── css/
│   │   ├── js/
│   │   ├── index.html
│   │   └── Registration.jsp
│   └── target/                 # Generated build output
└── .gitignore
```

---

## Security & Compliance

- **Password Safety** – Integrate a strong hashing algorithm (e.g., bcrypt) before production use.
- **Account Lockout** – Configurable failed-login thresholds to resist brute force attacks.
- **Input Validation** – Server-side validation and escaping reduce XSS and injection risks.
- **Session Hardening** – Invalidate sessions on logout; configure idle timeouts.
- **Transport Security** – Serve over HTTPS in production (Tomcat connector + TLS cert).
- **Configuration Hygiene** – Keep credentials outside source control (environment variables or secrets manager).

---

## Performance Notes

- **Caching** – Use Memcached for item listings, search results, and repetitive lookups.
- **Compression** – PJL compression filter reduces payload size and bandwidth.
- **Pooling** – Hibernate connection pool defaults to 10 connections; tune for workload.
- **Database Indexes** – Indexes on foreign keys and search columns keep queries fast.
- **Scalability** – Stateless REST layer enables horizontal scaling behind a load balancer.

---

## Troubleshooting

| Issue | Likely Cause | Fix |
|-------|--------------|-----|
| `404` on `/bidding/` | WAR name/context mismatch | Ensure WAR is named `bidding.war` or adjust URL |
| `java.sql.SQLException` | DB offline or wrong credentials | Start MySQL, verify `hibernate.cfg.xml`, test with `mysql -u bidding_user -p bidding_db` |
| Port 8080 busy | Another service using port | `lsof -i :8080` (macOS/Linux) or `netstat -ano | find "8080"` (Windows) |
| Emails not sending | SMTP credentials invalid or blocked | Recheck credentials, permit less-secure/app passwords, confirm port access |
| Build fails | Incorrect JDK or dependency cache | Ensure `java -version` shows 21, retry `mvn clean install -U` |
| Login locked | Failed login counter exceeded | Reset `failedLoginCount` for the account or wait for cooldown |

---

## Support & Contributions

- **Bug Reports** – Open issues at the [GitHub repository](https://github.com/mohanakrishnavh/bidding-web-application/issues) with logs and reproduction steps.
- **Feature Requests** – Submit a pull request from a dedicated feature branch.
- **Questions** – Contact `support@biddingapp.com` or use the issue tracker.

---

## License

Released under the **MIT License**. Include a `LICENSE` file when distributing the project.

---

**Happy bidding — may the best offer win!** 🎉
