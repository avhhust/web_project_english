document.getElementById("horizontal-scroller")
    .addEventListener('wheel', function(event) {
        if (event.deltaMode == event.DOM_DELTA_PIXEL) {
            let modifier = 1;
        } else if (event.deltaMode == event.DOM_DELTA_LINE) {
            let modifier = parseInt(getComputedStyle(this).lineHeight);
        } else if (event.deltaMode == event.DOM_DELTA_PAGE) {
            let modifier = this.clientHeight;
        }
        if (event.deltaY != 0) {
            // замена вертикальной прокрутки горизонтальной
            this.scrollLeft += modifier * event.deltaY;
            event.preventDefault();
        }
    });