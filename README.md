# Todo App - Full Stack Application

A full-stack todo application built with FastAPI (Python) backend and React (TypeScript) frontend, using PostgreSQL as the database.

## 📋 Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Development](#development)
- [API Documentation](#api-documentation)
- [Database Migrations](#database-migrations)
- [Testing](#testing)
- [Code Quality](#code-quality)

## 🎯 Overview

This is a study project demonstrating a modern full-stack application with:
- RESTful API backend with FastAPI
- React frontend with TypeScript
- PostgreSQL database
- Docker containerization
- Database migrations with Alembic
- Task and category management features

## 🛠 Tech Stack

### Backend
- **Python 3.10+**
- **FastAPI** - Modern web framework for building APIs
- **SQLAlchemy 1.4.47** - ORM for database operations
- **Alembic** - Database migration tool
- **PostgreSQL** - Relational database
- **Uvicorn** - ASGI server
- **Poetry** - Dependency management

### Frontend
- **React 18.2** - UI library
- **TypeScript 4.9** - Type-safe JavaScript
- **React Router DOM** - Client-side routing
- **Axios** - HTTP client
- **React Hook Form** - Form management
- **Tailwind CSS** - Utility-first CSS framework
- **React Icons** - Icon library
- **React Toastify** - Toast notifications

### DevOps
- **Docker & Docker Compose** - Containerization
- **ESLint & Prettier** - Code formatting and linting

## 📁 Project Structure

```
.
├── backend/                    # Backend application
│   ├── alembic/               # Database migrations
│   │   ├── versions/          # Migration files
│   │   └── env.py             # Alembic configuration
│   ├── app/                   # Main application code
│   │   ├── api/               # API endpoints
│   │   │   ├── categories.py # Category routes
│   │   │   ├── tasks.py      # Task routes
│   │   │   └── routers.py    # Router configuration
│   │   ├── crud/              # Database operations
│   │   ├── database/          # Database configuration
│   │   ├── schemas/           # Pydantic models
│   │   ├── config.py          # Application settings
│   │   └── main.py            # FastAPI application entry
│   ├── .env.app.dev           # Application environment variables
│   ├── .env.db.dev            # Database environment variables
│   ├── pyproject.toml         # Python dependencies
│   ├── alembic.ini            # Alembic configuration
│   └── Dockerfile-dev-backend # Backend Docker configuration
│
├── frontend/                   # Frontend application
│   ├── public/                # Static files
│   ├── src/                   # Source code
│   │   ├── components/        # Reusable components
│   │   ├── pages/             # Page components
│   │   │   ├── category/      # Category-related pages
│   │   │   └── task/          # Task-related pages
│   │   ├── services/          # API service layer
│   │   ├── hooks/             # Custom React hooks
│   │   ├── helpers/           # Utility functions
│   │   ├── interfaces/        # TypeScript interfaces
│   │   ├── App.tsx            # Main App component
│   │   └── index.tsx          # Application entry point
│   ├── package.json           # Node dependencies
│   ├── tsconfig.json          # TypeScript configuration
│   ├── tailwind.config.js     # Tailwind CSS configuration
│   └── Dockerfile-dev-frontend # Frontend Docker configuration
│
└── docker-compose.yml         # Docker Compose configuration
```

## ✅ Prerequisites

Before you begin, ensure you have the following installed:

- **Docker** (version 20.10+)
- **Docker Compose** (version 1.29+)

That's it! Docker will handle all other dependencies.

### For Local Development (Optional)

If you want to run services locally without Docker:

- **Python 3.10+** with Poetry
- **Node.js 16+** with npm
- **PostgreSQL 13+**

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd java-ts-study-todo-app
```

### 2. Environment Configuration

The project includes development environment files:
- `backend/.env.app.dev` - Backend application settings
- `backend/.env.db.dev` - Database credentials

These files are already configured for Docker development.

### 3. Start the Application with Docker

```bash
docker-compose up --build
```

This command will:
- Build the backend and frontend Docker images
- Start PostgreSQL database
- Run database migrations
- Start the backend API server on `http://localhost:8080`
- Start the frontend development server on `http://localhost:3000`

### 4. Access the Application

- **Frontend**: http://localhost:3000
- **Backend API**: http://localhost:8080
- **API Documentation**: http://localhost:8080/docs (Swagger UI)
- **Alternative API Docs**: http://localhost:8080/redoc (ReDoc)

## 💻 Development

### Running Services Individually

#### Backend Only

```bash
docker-compose up postgres app_backend
```

#### Frontend Only (requires backend to be running)

```bash
docker-compose up app_frontend
```

### Local Development Without Docker

#### Backend

```bash
cd backend

# Install dependencies
poetry install

# Set up environment variables
export $(cat .env.app.dev | xargs)
export $(cat .env.db.dev | xargs)

# Run migrations
alembic upgrade head

# Start the server
poetry run python -m app.main
```

#### Frontend

```bash
cd frontend

# Install dependencies
npm install

# Start development server
npm start
```

### Hot Reload

Both frontend and backend support hot reload in development mode:
- **Backend**: Changes to Python files will automatically reload the server
- **Frontend**: Changes to React files will automatically refresh the browser

### Stopping the Application

```bash
docker-compose down
```

To remove volumes (database data):

```bash
docker-compose down -v
```

## 📚 API Documentation

The backend provides interactive API documentation:

- **Swagger UI**: http://localhost:8080/docs
  - Interactive API testing interface
  - Try out endpoints directly from the browser

- **ReDoc**: http://localhost:8080/redoc
  - Clean, readable API documentation

### Main API Endpoints

- `GET /api/tasks` - List all tasks
- `POST /api/tasks` - Create a new task
- `GET /api/tasks/{id}` - Get a specific task
- `PUT /api/tasks/{id}` - Update a task
- `DELETE /api/tasks/{id}` - Delete a task

- `GET /api/categories` - List all categories
- `POST /api/categories` - Create a new category
- `GET /api/categories/{id}` - Get a specific category
- `PUT /api/categories/{id}` - Update a category
- `DELETE /api/categories/{id}` - Delete a category

## 🗄 Database Migrations

The project uses Alembic for database migrations.

### Create a New Migration

```bash
# Inside the backend container
docker-compose exec app_backend alembic revision --autogenerate -m "Description of changes"
```

### Apply Migrations

```bash
docker-compose exec app_backend alembic upgrade head
```

### Rollback Migration

```bash
docker-compose exec app_backend alembic downgrade -1
```

### View Migration History

```bash
docker-compose exec app_backend alembic history
```

## 🧪 Testing

### Frontend Tests

```bash
cd frontend
npm test
```

Run tests in watch mode (default for Create React App).

### Backend Tests

```bash
cd backend
poetry run pytest
```

## 🎨 Code Quality

### Frontend Linting

```bash
cd frontend

# Run ESLint
npm run lint

# Format code with Prettier
npx prettier --write "src/**/*.{ts,tsx}"
```

### Backend Code Quality

```bash
cd backend

# Format code with Black (if configured)
poetry run black app/

# Type checking with mypy (if configured)
poetry run mypy app/
```

## 🔧 Troubleshooting

### Port Already in Use

If ports 3000, 8080, or 5432 are already in use, you can modify them in `docker-compose.yml`:

```yaml
ports:
  - "3001:3000"  # Change host port (left side)
```

### Database Connection Issues

1. Ensure PostgreSQL container is running:
   ```bash
   docker-compose ps
   ```

2. Check database logs:
   ```bash
   docker-compose logs postgres
   ```

3. Restart the database:
   ```bash
   docker-compose restart postgres
   ```

### Frontend Not Loading

1. Clear npm cache and reinstall:
   ```bash
   docker-compose down
   docker-compose up --build app_frontend
   ```

2. Check frontend logs:
   ```bash
   docker-compose logs app_frontend
   ```

### Backend API Errors

1. Check backend logs:
   ```bash
   docker-compose logs app_backend
   ```

2. Verify migrations are up to date:
   ```bash
   docker-compose exec app_backend alembic current
   ```

## 📝 Contributing

1. Create a feature branch
2. Make your changes
3. Test your changes
4. Submit a pull request

## 📄 License

This is a study project.

## 👥 Authors

- Artem Jasan <artem.jasan@applifting.cz>

---

**Happy Coding! 🚀**
