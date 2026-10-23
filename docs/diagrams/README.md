# Diagrams

All diagram labels in this directory are in English. Matching Russian versions are in [docs-for-us/diagrams/](../../docs-for-us/diagrams/).

## Available diagrams

- [Role-based application flow](bpmn/application-process.mmd) - Microsoft sign-in, faculty, expert, administrator, and automatic processing layers.
- [Database ERD](erd/database.mmd) - initial logical model for users, competitions, applications, evidence versions, expert evaluations, and AI checks.

## Directory conventions

- bpmn/ - process diagrams. The current Mermaid flowchart is a role-based user flow, not a formal BPMN 2.0 model. Store formal editor-generated BPMN models as .bpmn files.
- erd/ - entity relationship diagrams.
- uml/ - use case, component, and sequence diagrams to be added later.
- context/ - system context diagrams to be added later.
- exports/ - SVG and PNG files for presentations.

Save Mermaid code in .mmd files without Markdown code fences. Use matching base names for source and exported files, for example erd/database.mmd and exports/database.svg.

The ERD is an initial logical design, not a migration script. Enforce one application per applicant and competition, consistent competition and rule-set references, and one current evaluation revision per expert assignment in the implementation. The corporate subject identifier links a user to Microsoft Entra ID; Microsoft passwords are not stored locally.
