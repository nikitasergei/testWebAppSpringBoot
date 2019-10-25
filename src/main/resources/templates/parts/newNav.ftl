<div class="form-group mt-0">
    <div class="btn-group btn-group-toggle" data-toggle="buttons">
        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
           aria-expanded="false">Поиск по:</a>
        <div class="dropdown-menu" id="dropMenu">
            <div class="dropdown-item" name="city">городу</div>
            <div class="dropdown-item" name="address">адресу</div>
            <div class="dropdown-item" name="regNum">регистрационному номеру</div>
            <div class="dropdown-item" name="factNum">заводскому номеру</div>
            <div class="dropdown-item" name="activationDate">дате ввода в эксплуатацию</div>
        </div>
        <label style="display: none" id="filtVal">ygjg</label>

        <script>
            var dropMenu = document.getElementById("dropMenu");
            dropMenu.onclick = function (ev) {
                var target = ev.target;
                var name = target.getAttribute('name');
                showInput(name);
            };

            function showInput(name) {
                var formDiv = document.getElementById("formDiv");
                formDiv.style.display = "";
                var inp = document.getElementById("inp");
                inp.setAttribute("value", name);
            }
        </script>
    </div>
    <div class="form-group mt-3" id="formDiv" style="display: none">
        <form method="get" action="/lifts">
            <div class="form-group" style="width: 30%">
                <input class="form-control" type="text" name="filter"/>
                <input type="hidden" id="inp" name="filterBy">
            </div>
            <div class="form-group mt-2">
                <button type="submit" class="btn btn-primary">Поиск</button>
            </div>
        </form>
    </div>
</div>