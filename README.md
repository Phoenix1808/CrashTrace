# 🚨 CrashTrace SDK



CrashTrace is a lightweight Android crash analytics SDK designed to capture application crashes along with a **timeline of user interactions leading up to the crash**.
Traditional crash reporters typically provide only stack traces and device information. While useful, this information often lacks context about **what the user actually did before the crash happened**.
CrashTrace solves this problem by automatically tracking user interactions and screen navigation throughout the session and attaching that timeline to the crash report.
This allows developers to reconstruct the user journey that led to the crash and significantly reduce debugging time. 

---

# 🎯 Project Goal

The primary goal of CrashTrace is to provide developers with **context-aware crash reports**.
Instead of only seeing where the crash occurred, developers can see the full interaction sequence that caused the crash.

Example crash timeline:

```
Session: 26aca110

Timeline:
Screen_open LoginActivity
ui_click loginButton
Screen_open HomeActivity
ui_click profileCard
ui_click editProfileButton
Crash
```

This helps developers answer questions like:

- What screen was the user on?
- What actions did the user perform?
- What sequence of events caused the crash?

---

# ✨ Features Implemented

CrashTrace SDK currently includes the following core features.

## SDK Initialization

CrashTrace starts tracking when initialized inside the application.

```kotlin
CrashTrace.init(applicationContext)
```

This setup automatically enables:

- session tracking
- activity lifecycle tracking
- UI interaction tracking
- crash interception

---

## Session Tracking

Each time the application launches, CrashTrace generates a unique session ID.

Example:

```
Session: 26aca110-4d91-4f07-b620-4cc0e2ba5969
```

This allows events and crashes to be grouped within a single user session.

Session tracking is handled by:

```
SessionManager.kt
```

---

## Automatic Screen Tracking

CrashTrace uses Android's `Application.ActivityLifecycleCallbacks` to detect screen changes automatically.

When an activity becomes visible, the SDK records an event.

Example:

```
Event: Screen_open | Screen: MainActivity
```

This feature works automatically without requiring developers to manually instrument each activity.

Handled by:

```
ActivityTracker.kt
```

---

## UI Interaction Tracking

CrashTrace automatically tracks interactions with clickable UI elements.

Supported interactions include:

- Button clicks
- ImageView clicks
- CardView clicks
- FloatingActionButton clicks
- Any clickable view

Example event:

```
ui_click loginButton
```

The SDK traverses the entire view hierarchy of the activity and attaches listeners to clickable elements.

Handled by:

```
ClickTrackers.kt
```

---

## Listener Wrapping using Reflection

Many applications already attach their own click listeners to views.

CrashTrace uses **reflection** to retrieve the existing listener and wrap it without breaking existing behavior.

Execution flow::

```
User Click
↓
CrashTrace logs interaction
↓
Original click listener executes
```

This ensures:

- zero interference with app logic
- seamless integration

Handled by:

```
ReflectionUtils.kt
```

---

## Event Buffer

User interactions and screen transitions are stored inside an event buffer.

Example buffer contents:

```
Screen_open LoginActivity
ui_click loginButton
Screen_open HomeActivity
ui_click profileCard
```

The buffer maintains the most recent events before a crash occurs.

Handled by:

```
EventBuffer.kt
```

---

## Crash Interception

CrashTrace intercepts application crashes using Android's uncaught exception handler.

```kotlin
Thread.setDefaultUncaughtExceptionHandler()
```

When a crash occurs the SDK captures:

- crash message
- current session
- recent user events

Example output:

```
App Crash Detected
Crash Msg: Test Crash
Session: 26aca110
```

Handled by:

```
CrashHandler.kt
```

---

## Crash Report Builder

CrashTrace constructs a structured crash report object containing all relevant data.

Example structure:

```
CrashReport(
 sessionId,
 crashMessage,
 screen,
 timeline,
 timestamp
)
```

Handled by:

```
CrashReport.kt
CrashReportBuilder.kt
```

---

## Crash Report Serialization

The crash report is converted into JSON format so that it can be transmitted to a backend service.

Example JSON output:

```json
{
  "sessionId": "26aca110",
  "crashMessage": "Test Crash",
  "screen": "MainActivity",
  "timestamp": 171060000,
  "timeline": [
    "Screen_open MainActivity",
    "ui_click loginButton"
  ]
}
```

Handled by:

```
CrashReportSerializer.kt
```

---

# 🧱 Current SDK Architecture

```
crashtrace-sdk
│
core
│
├── CrashTrace.kt
├── CrashHandler.kt
├── SessionManager.kt
├── ActivityTracker.kt
├── ClickTrackers.kt
├── ReflectionUtils.kt
├── EventTrackers.kt
├── EventBuffer.kt
├── CrashReport.kt
├── CrashReportBuilder.kt
└── CrashReportSerializer.kt
```

---

# ⚙️ SDK Workflow

The complete SDK execution pipeline works as follows:

```
App Start
↓
CrashTrace.init()
↓
Session Created
↓
Activity Lifecycle Hooked
↓
Screen Events Captured
↓
User Interaction Events Captured
↓
Events Stored in EventBuffer
↓
Crash Occurs
↓
CrashHandler Intercepts Crash
↓
CrashReportBuilder Builds Report
↓
CrashReportSerializer Converts to JSON
```

---

# 🚀 Upcoming Features

The current SDK implements the core crash tracking engine. Future development will include:

### Device Information Collection

Crash reports will include additional metadata such as:

- device model
- android version
- app version
- manufacturer

Example:

```json
{
  "device": "Pixel 6",
  "android": "14",
  "appVersion": "1.0.0"
}
```

---

### Crash Upload API

The SDK will send crash reports to a backend server.

```
POST /crash-report
```

This allows centralized crash storage and monitoring.

---

### Crash Dashboard

A web interface will allow developers to view crash reports and inspect event timelines.

Example flow:

```
Crash List
↓
Open Crash
↓
View User Timeline
```

---

### Crash Analytics

Future analytics may include:

- most crashing screen
- most frequent crash flow
- crash frequency statistics
- session insights

---

# 🔥 What Makes CrashTrace Different

Most crash analytics tools focus on:

```
stacktrace
exception type
device info
```

CrashTrace focuses on **user context**.

Key differentiators:

### Event Timeline

CrashTrace reconstructs the **exact user journey before a crash**.

---

### Lightweight SDK

The SDK is designed to be minimal and dependency-free.

---

### Automatic Interaction Tracking

Developers do not need to manually instrument UI events.

---

### Reflection-based Listener Wrapping

CrashTrace captures interactions even when existing click listeners are present.

---

### Simple Backend Integration

Crash reports are already structured as JSON, making server integration straightforward.

---

# 📊 Project Status

```
SDK Core        ████████████████████ 95%
Backend API     ░░░░░░░░░░░░░░░░░░░░
Dashboard       ░░░░░░░░░░░░░░░░░░░░
```

The core SDK engineering is essentially complete.

Upcoming work will focus on backend services and crash visualization tools.

---

# 📚 Learning Outcomes

This project demonstrates key Android engineering concepts including:

- Android SDK development
- Activity lifecycle hooks
- view hierarchy traversal
- reflection in Android
- event buffering systems
- crash interception mechanisms
- JSON serialization
- modular SDK architecture design

---

# 👨‍💻 Author

**Divyansh Goyal**  
Android Developer

---

# 📄 License

MIT License
