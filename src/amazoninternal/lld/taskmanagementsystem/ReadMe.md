Task Management System.

1. Functional Requirements
   Hierarchical Task Structure: Support for parent tasks and subtasks using the Composite Pattern.

State Management: Tasks must exist in one of four states: TODO, IN_PROGRESS, DONE, or BLOCKED.

Validation Rules: * Specific transitions are enforced (e.g., DONE tasks can only go back to TODO).

A parent task cannot be marked DONE unless all its subtasks are also DONE.

Activity Logging: Every key event (creation, status changes, assignment) must be recorded in a chronological activity log.

Metadata: Tasks must support a title, unique ID, assignee, and priority level (LOW to CRITICAL).

Search & Filtering: Users must be able to filter tasks by status, priority, and assignee.

2. Design Requirements (LLD)
   Extensible Filtering: Use of the Strategy Pattern to allow for composable filters (e.g., "Find all tasks that are HIGH priority AND assigned to Alice").

Case Insensitivity: (Context from earlier autocomplete discussion) Inputs should be treated consistently.

Encapsulation: Internal state (like the status and list of subtasks) must be protected, with changes handled through controlled public methods.

Separation of Concerns: A TaskManager service manages the collection of task lists, while the Task entity handles its own internal logic and hierarchy.

3. Technical Constraints
   Stateless Filtering: Strategies should ideally be decoupled from the TaskManager logic.

Defensive Programming: Proper initialization of collections to prevent NullPointerExceptions and validation of state before mutation.