 <!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Todo List App</title>
  <style>
    * {
      box-sizing: border-box;
      font-family: 'Segoe UI', sans-serif;
    }

    body {
      margin: 0;
      padding: 0;
      background-color: #f0f2f5;
      transition: background 0.3s, color 0.3s;
    }

    .dark-mode {
      background-color: #181818;
      color: white;
    }

    .container {
      max-width: 600px;
      margin: 60px auto;
      padding: 20px;
      background-color: white;
      border-radius: 16px;
      box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
    }

    .dark-mode .container {
      background-color: #242424;
    }

    h1 {
      text-align: center;
      margin-bottom: 20px;
    }

    .input-group {
      display: flex;
      gap: 10px;
      margin-bottom: 20px;
    }

    input[type="text"],
    input[type="date"] {
      flex: 1;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 8px;
    }

    button {
      padding: 10px 14px;
      border: none;
      border-radius: 8px;
      background-color: #007bff;
      color: white;
      cursor: pointer;
    }

    button:hover {
      background-color: #0056b3;
    }

    .task-list {
      list-style: none;
      padding: 0;
      margin: 0;
    }

    .task-item {
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 12px;
      margin-bottom: 10px;
      background-color: #f8f9fa;
      border-radius: 10px;
    }

    .dark-mode .task-item {
      background-color: #333;
    }

    .task-content {
      display: flex;
      align-items: center;
      gap: 10px;
    }

    .task-content span {
      font-size: 16px;
    }

    .task-item.completed span {
      text-decoration: line-through;
      opacity: 0.6;
    }

    .filters {
      margin-top: 20px;
      display: flex;
      gap: 10px;
      justify-content: space-between;
    }

    .filters button {
      flex: 1;
      background-color: #6c757d;
    }

    .filters button.active {
      background-color: #007bff;
    }

    .dark-toggle {
      margin-top: 20px;
      text-align: right;
    }

    .delete-btn {
      background-color: #dc3545;
      border: none;
      color: white;
      border-radius: 6px;
      padding: 5px 10px;
      cursor: pointer;
    }

    .delete-btn:hover {
      background-color: #b02a37;
    }

    .date-label {
      font-size: 13px;
      opacity: 0.7;
      margin-top: 4px;
    }

  </style>
</head>
<body>
  <div class="container">
    <h1>📝 내 투두 리스트</h1>
    <div class="input-group">
      <input type="text" id="todo-input" placeholder="할 일을 입력하세요..." />
      <input type="date" id="todo-date" />
      <button onclick="addTask()">추가</button>
    </div>
    <ul class="task-list" id="task-list"></ul>

    <div class="filters">
      <button onclick="setFilter('all')" id="filter-all" class="active">전체</button>
      <button onclick="setFilter('active')" id="filter-active">미완료</button>
      <button onclick="setFilter('completed')" id="filter-completed">완료</button>
    </div>

    <div class="dark-toggle">
      <button onclick="toggleDarkMode()">🌙 다크 모드</button>
    </div>
  </div>

  <script>
    let tasks = JSON.parse(localStorage.getItem("tasks")) || [];
    let filter = 'all';

    const taskList = document.getElementById('task-list');

    function saveTasks() {
      localStorage.setItem("tasks", JSON.stringify(tasks));
    }

    function addTask() {
      const input = document.getElementById("todo-input");
      const dateInput = document.getElementById("todo-date");

      const text = input.value.trim();
      const dueDate = dateInput.value;

      if (!text) return;

      tasks.push({
        id: Date.now(),
        text,
        dueDate,
        completed: false,
      });

      input.value = "";
      dateInput.value = "";

      saveTasks();
      renderTasks();
    }

    function toggleTask(id) {
      tasks = tasks.map(t => t.id === id ? { ...t, completed: !t.completed } : t);
      saveTasks();
      renderTasks();
    }

    function deleteTask(id) {
      tasks = tasks.filter(t => t.id !== id);
      saveTasks();
      renderTasks();
    }

    function setFilter(newFilter) {
      filter = newFilter;

      document.querySelectorAll('.filters button').forEach(btn => btn.classList.remove('active'));
      document.getElementById(`filter-${newFilter}`).classList.add('active');

      renderTasks();
    }

    function toggleDarkMode() {
      document.body.classList.toggle('dark-mode');
    }

    function renderTasks() {
      taskList.innerHTML = '';

      const filtered = tasks
        .filter(task => {
          if (filter === 'active') return !task.completed;
          if (filter === 'completed') return task.completed;
          return true;
        })
        .sort((a, b) => {
          if (a.dueDate && b.dueDate) return new Date(a.dueDate) - new Date(b.dueDate);
          return 0;
        });

      filtered.forEach(task => {
        const li = document.createElement('li');
        li.className = 'task-item' + (task.completed ? ' completed' : '');

        const content = document.createElement('div');
        content.className = 'task-content';

        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.checked = task.completed;
        checkbox.addEventListener('change', () => toggleTask(task.id));

        const span = document.createElement('span');
        span.textContent = task.text;

        content.appendChild(checkbox);
        content.appendChild(span);

        const rightContent = document.createElement('div');

        if (task.dueDate) {
          const dateSpan = document.createElement('div');
          dateSpan.className = 'date-label';
          dateSpan.textContent = '🗓 ' + task.dueDate;
          rightContent.appendChild(dateSpan);
        }

        const deleteBtn = document.createElement('button');
        deleteBtn.textContent = '삭제';
        deleteBtn.className = 'delete-btn';
        deleteBtn.onclick = () => deleteTask(task.id);
        rightContent.appendChild(deleteBtn);

        li.appendChild(content);
        li.appendChild(rightContent);

        taskList.appendChild(li);
      });
    }

    renderTasks();
  </script>
</body>
</html>
 <!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Todo List App</title>
  <style>
    * {
      box-sizing: border-box;
      font-family: 'Segoe UI', sans-serif;
    }

    body {
      margin: 0;
      padding: 0;
      background-color: #f0f2f5;
      transition: background 0.3s, color 0.3s;
    }

    .dark-mode {
      background-color: #181818;
      color: white;
    }

    .container {
      max-width: 600px;
      margin: 60px auto;
      padding: 20px;
      background-color: white;
      border-radius: 16px;
      box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
    }

    .dark-mode .container {
      background-color: #242424;
    }

    h1 {
      text-align: center;
      margin-bottom: 20px;
    }

    .input-group {
      display: flex;
      gap: 10px;
      margin-bottom: 20px;
    }

    input[type="text"],
    input[type="date"] {
      flex: 1;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 8px;
    }

    button {
      padding: 10px 14px;
      border: none;
      border-radius: 8px;
      background-color: #007bff;
      color: white;
      cursor: pointer;
    }

    button:hover {
      background-color: #0056b3;
    }

    .task-list {
      list-style: none;
      padding: 0;
      margin: 0;
    }

    .task-item {
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 12px;
      margin-bottom: 10px;
      background-color: #f8f9fa;
      border-radius: 10px;
    }

    .dark-mode .task-item {
      background-color: #333;
    }

    .task-content {
      display: flex;
      align-items: center;
      gap: 10px;
    }

    .task-content span {
      font-size: 16px;
    }

    .task-item.completed span {
      text-decoration: line-through;
      opacity: 0.6;
    }

    .filters {
      margin-top: 20px;
      display: flex;
      gap: 10px;
      justify-content: space-between;
    }

    .filters button {
      flex: 1;
      background-color: #6c757d;
    }

    .filters button.active {
      background-color: #007bff;
    }

    .dark-toggle {
      margin-top: 20px;
      text-align: right;
    }

    .delete-btn {
      background-color: #dc3545;
      border: none;
      color: white;
      border-radius: 6px;
      padding: 5px 10px;
      cursor: pointer;
    }

    .delete-btn:hover {
      background-color: #b02a37;
    }

    .date-label {
      font-size: 13px;
      opacity: 0.7;
      margin-top: 4px;
    }

  </style>
</head>
<body>
  <div class="container">
    <h1>📝 내 투두 리스트</h1>
    <div class="input-group">
      <input type="text" id="todo-input" placeholder="할 일을 입력하세요..." />
      <input type="date" id="todo-date" />
      <button onclick="addTask()">추가</button>
    </div>
    <ul class="task-list" id="task-list"></ul>

    <div class="filters">
      <button onclick="setFilter('all')" id="filter-all" class="active">전체</button>
      <button onclick="setFilter('active')" id="filter-active">미완료</button>
      <button onclick="setFilter('completed')" id="filter-completed">완료</button>
    </div>

    <div class="dark-toggle">
      <button onclick="toggleDarkMode()">🌙 다크 모드</button>
    </div>
  </div>

  <script>
    let tasks = JSON.parse(localStorage.getItem("tasks")) || [];
    let filter = 'all';

    const taskList = document.getElementById('task-list');

    function saveTasks() {
      localStorage.setItem("tasks", JSON.stringify(tasks));
    }

    function addTask() {
      const input = document.getElementById("todo-input");
      const dateInput = document.getElementById("todo-date");

      const text = input.value.trim();
      const dueDate = dateInput.value;

      if (!text) return;

      tasks.push({
        id: Date.now(),
        text,
        dueDate,
        completed: false,
      });

      input.value = "";
      dateInput.value = "";

      saveTasks();
      renderTasks();
    }

    function toggleTask(id) {
      tasks = tasks.map(t => t.id === id ? { ...t, completed: !t.completed } : t);
      saveTasks();
      renderTasks();
    }

    function deleteTask(id) {
      tasks = tasks.filter(t => t.id !== id);
      saveTasks();
      renderTasks();
    }

    function setFilter(newFilter) {
      filter = newFilter;

      document.querySelectorAll('.filters button').forEach(btn => btn.classList.remove('active'));
      document.getElementById(`filter-${newFilter}`).classList.add('active');

      renderTasks();
    }

    function toggleDarkMode() {
      document.body.classList.toggle('dark-mode');
    }

    function renderTasks() {
      taskList.innerHTML = '';

      const filtered = tasks
        .filter(task => {
          if (filter === 'active') return !task.completed;
          if (filter === 'completed') return task.completed;
          return true;
        })
        .sort((a, b) => {
          if (a.dueDate && b.dueDate) return new Date(a.dueDate) - new Date(b.dueDate);
          return 0;
        });

      filtered.forEach(task => {
        const li = document.createElement('li');
        li.className = 'task-item' + (task.completed ? ' completed' : '');

        const content = document.createElement('div');
        content.className = 'task-content';

        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.checked = task.completed;
        checkbox.addEventListener('change', () => toggleTask(task.id));

        const span = document.createElement('span');
        span.textContent = task.text;

        content.appendChild(checkbox);
        content.appendChild(span);

        const rightContent = document.createElement('div');

        if (task.dueDate) {
          const dateSpan = document.createElement('div');
          dateSpan.className = 'date-label';
          dateSpan.textContent = '🗓 ' + task.dueDate;
          rightContent.appendChild(dateSpan);
        }

        const deleteBtn = document.createElement('button');
        deleteBtn.textContent = '삭제';
        deleteBtn.className = 'delete-btn';
        deleteBtn.onclick = () => deleteTask(task.id);
        rightContent.appendChild(deleteBtn);

        li.appendChild(content);
        li.appendChild(rightContent);

        taskList.appendChild(li);
      });
    }

    renderTasks();
  </script>
</body>
</html>
