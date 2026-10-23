# AITU Teaching Excellence Awards

A platform for AITU faculty applications, supporting-document checks, and expert evaluation.

## Repository structure

- [backend/](backend/) - Java + Spring Boot backend.
- [frontend/](frontend/) - web interface; the frontend developer will select the technology stack later.
- [ai-service/](ai-service/) - Python + FastAPI document analysis service.
- [docs/](docs/) - English requirements, diagrams, research, UX, testing, and presentation materials.
- [docs-for-us/](docs-for-us/) - Russian documentation and diagram translations for the team.
- [infra/](infra/) - deployment and infrastructure configuration.
- [.github/workflows/](.github/workflows/) - future automated builds and checks.

Database: PostgreSQL. Authentication: corporate AITU Microsoft accounts through Microsoft Entra ID.

## Project status

This repository currently contains the project skeleton and initial design diagrams. Application code and runnable service configuration have not been added yet.
Add pom.xml, pyproject.toml, Dockerfiles, and compose.yaml when initializing the services. Each service README will contain its setup and run instructions.

## Documentation languages

Repository READMEs and documents under docs/ are in English. Russian explanations and matching diagram versions belong in docs-for-us/. When updating a diagram, update both language versions together.

## File conventions

- Keep secrets and local settings in .env, which is excluded from Git.
- Add only variable names and safe examples to .env.example.
- Do not commit actual faculty evidence documents.
- Store Mermaid sources as .mmd files and presentation exports under diagrams/exports/ in the corresponding language directory.
- .gitkeep files preserve empty directories in Git and can be removed once those directories contain project files.
