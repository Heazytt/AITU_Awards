# AI Service

Python + FastAPI. Analyzes uploaded documents and extracts achievement years with supporting text.

- app/ — service source code.
- tests/ — tests.

Eligibility checks use calendar years. The main backend compares extracted years against the configured five-year window and confirmed AITU employment periods.
AI does not assign official scores or select winners. Documents requiring clarification or unavailable AI processing remain subject to human review.

pyproject.toml, Dockerfile, and run instructions will be added when the service is initialized.
