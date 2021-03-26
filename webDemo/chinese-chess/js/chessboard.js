$(document).ready(function () {
	var gameover = false;
	var gameovermsg = '';
	const xCoordLength = 9;   // 棋盘行数
	const yCoordLength = 10;  // 棋盘列数
	// 各色小兵前进偏移量
	const goY = {
		'RED': 1,
		'BLACK': -1
	}
	var board = [];
	var exeColor = ['RED', 'BLACK']; // 红棋先行
	var exeIndex = 0;
	build();

	function build() {
		//第一子位置
		let left = 50;
		let top = 10;
		//棋子间隔
		let interva = 79;
		initChess();
		for (let i = 0; i < board.length; i++) {
			let coord = getCoord(i);
			let cell = document.createElement('div');
			cell.classList.add('cell');
			let color = getChessColor(i);
			color = color == null ? '' : color;
			let chess = getChessValue(i);
			chess = chess == null ? '' : chess;
			if (color) {
				cell.classList.add(color);
				cell.innerText = chess;
			}
			cell.setAttribute("style", "left:" + (left + interva * coord.x) + "px;top:" + (top + interva * coord.y) + "px");
			cell.addEventListener('click', () => clickChess(i));
			$(".chessboard").append(cell);

		}
	}

	/**
	 * 初始化象棋棋盘9*10
	 */
	function initChess() {
		board = [
			{ RED: '車' }, { RED: '馬' }, { RED: '相' }, { RED: '仕' }, { RED: '帥' }, { RED: '仕' }, { RED: '相' }, { RED: '馬' }, { RED: '車' },
			{}, {}, {}, {}, {}, {}, {}, {}, {},
			{}, { RED: '炮' }, {}, {}, {}, {}, {}, { RED: '炮' }, {},
			{ RED: '兵' }, {}, { RED: '兵' }, {}, { RED: '兵' }, {}, { RED: '兵' }, {}, { RED: '兵' },
			{}, {}, {}, {}, {}, {}, {}, {}, {},
			{}, {}, {}, {}, {}, {}, {}, {}, {},
			{ BLACK: '卒' }, {}, { BLACK: '卒' }, {}, { BLACK: '卒' }, {}, { BLACK: '卒' }, {}, { BLACK: '卒' },
			{}, { BLACK: '炮' }, {}, {}, {}, {}, {}, { BLACK: '炮' }, {},
			{}, {}, {}, {}, {}, {}, {}, {}, {},
			{ BLACK: '车' }, { BLACK: '馬' }, { BLACK: '象' }, { BLACK: '士' }, { BLACK: '將' }, { BLACK: '士' }, { BLACK: '象' }, { BLACK: '馬' }, { BLACK: '车' },
		];
		if (getChessColor(0) == 'BLACK') {
			goY['RED'] = -1;
			blackGoY = 1;
		} else {

		}
	}

	// 当前选中棋子的索引
	var checkChessIndex = -1;

	// 添加单元格事件
	function clickChess(index) {
		if (gameover) {
			gameoverTip();
		}
		// 未移动
		if (checkChessIndex == index) {
			return;
		}

		// 选中的是当前待执行的棋子
		if (getChessColor(index) == getExeChessColor()) {
			let divData;
			if (checkChessIndex != -1) {
				divData = $(".chessboard").children("div:eq(" + checkChessIndex + ")");
				divData.removeClass("selected");
			}
			checkChessIndex = index;
			divData = $(".chessboard").children("div:eq(" + index + ")");
			divData.addClass("selected");
			return;
		}
		if (checkChessIndex != -1) {
			move(index);
			return;
		}
		checkChessIndex = -1;
		return;
	}


	// 移动棋子
	function move(index) {
		// 选中棋子的值
		let chessValue = getCheckChessValue();
		let moveSuccess = false;
		moveCoord(index);
		switch (chessValue) {
			case '车':
			case '車': moveSuccess = che(index); break;
			case '馬': moveSuccess = ma(index); break;
			case '炮': moveSuccess = pao(index); break;
			case '相':
			case '象': moveSuccess = xiang(index); break;
			case '仕':
			case '士': moveSuccess = shi(index); break;
			case '帥':
			case '將': moveSuccess = jiang(index); break;
			case '兵':
			case '卒': moveSuccess = bing(index); break;
		}
		console.log('moveSuccess:' + moveSuccess)
		if (moveSuccess) {
			rebuild(index);
			// 成功移动后，切换棋手
			exeIndex = 1 - exeIndex;
			checkChessIndex = -1;
		}
	}

	/**
	 * 成功移动后，更新html内容
	 * @param {落点index}} index 
	 */
	function rebuild(index) {
		// 执行棋子的索引 checkChessIndex
		// 落点位置的索引 index
		let checkChessHtml = document.getElementsByClassName("cell")[checkChessIndex];
		let dropPointChessHtml = document.getElementsByClassName("cell")[index];
		let checkChessValue = getCheckChessValue();
		let checkChessColor = getCheckChessColor();
		// 清空起点数据
		checkChessHtml.innerHTML = '';
		checkChessHtml.classList.remove(checkChessColor);
		// 落点位置有数据，则清空
		console.log('drop' + exeColor[1 - exeIndex]);
		if (dropPointChessHtml.classList.contains(exeColor[1 - exeIndex])) {
			let chessValue = getChessValue(index);
			if (chessValue == '帥' || chessValue == '將') {
				let winColor = getCheckChessColor();
				gameovermsg = '游戏结束,';
				if (winColor == 'RED') {
					gameovermsg += '红棋获胜!'
				} else {
					gameovermsg += '黑棋获胜!'
				}
				gameover = true;
			}
			dropPointChessHtml.innerHTML = '';
			dropPointChessHtml.classList.remove(exeColor[1 - exeIndex]);
			if (gameover) {
				gameoverTip();
			}
		}
		// 添加落点数据
		dropPointChessHtml.innerText = checkChessValue;
		dropPointChessHtml.classList.add(checkChessColor);
		// 更新board中的数据
		board[index] = copy(board[checkChessIndex]);
		board[checkChessIndex] = {};
	}

	// 移动前的x、y坐标
	let beforeX = -1;
	let beforeY = -1;
	// 移动后的x、y坐标
	let afterX = -1;
	let afterY = -1;

	// 移动前后坐标
	function moveCoord(index) {
		beforeX = getCoord(checkChessIndex).x;
		beforeY = getCoord(checkChessIndex).y;
		afterX = getCoord(index).x;
		afterY = getCoord(index).y;
	}

	/**
	 * 车
	 * @param {落点索引} index 
	 */
	function che(index) {
		// 不是直走
		if (beforeX != afterX && beforeY != afterY) {
			return false;
		}
		// 行走路径的索引
		let bigIndex = index > checkChessIndex ? index : checkChessIndex;
		let smallIndex = index > checkChessIndex ? checkChessIndex : index;
		let nextChessSpan = 0;
		// 延x直走,y不变
		if (beforeY == afterY) {
			// 下一个棋子距当前棋子的位移
			nextChessSpan = 1;
		}
		// 延y直走,x不变
		if (beforeX == afterX) {
			// 下一个棋子距当前棋子的位移
			nextChessSpan = xCoordLength;
		}
		// 查看中间是否有棋子
		for (let i = smallIndex + nextChessSpan; i <= bigIndex; i += nextChessSpan) {
			if (i == checkChessIndex) {
				return true;
			}
			// 落点位置是否是友方棋子
			if (i == bigIndex && isFriendChess(i)) {
				return false;
			}
			// 如果路径中间存在棋子
			if (i != bigIndex && isHasChess(i)) {
				return false;
			}

		}
		return true;
	}

	/**
	 * 马
	 * @param {落点索引} index 
	 */
	function ma(index) {
		// 马走日 
		if(!((Math.abs(beforeX - afterX) == 2 && Math.abs(beforeY - afterY) == 1)
		|| (Math.abs(beforeX - afterX) == 1 && Math.abs(beforeY - afterY) == 2))) {
			return false;
		}
		let midX = (beforeX + afterX) / 2;
		let midY = (beforeY + afterY) / 2;

		if(Math.abs(beforeX - afterX) == 2 
			&& !getChessValue(getIndex(midX,beforeY))
			&&  (getChessValue(index) == null || getCheckChessColor != getChessColor(index)) ) {
			return true;
		} 
		if(Math.abs(beforeY - afterY) == 2 
			&& !getChessValue(getIndex(beforeX,midY))
			&&  (getChessValue(index) == null || getCheckChessColor != getChessColor(index)) ) {
			return true;
		} 
		return false;

	}

	/**
	 * 象
	 * @param {落点索引} index 
	 */
	function xiang(index) {
		let midLine = yCoordLength / 2;
		// 象飞田
		if (Math.abs(beforeX - afterX) == 2 && Math.abs(beforeY - afterY) == 2) {
			let midX = (beforeX + afterX) / 2;
			let midY = (beforeY + afterY) / 2;
			let midIndex = getIndex(midX, midY);
			console.log(midIndex);
			// 有阻挡
			if (getChessValue(midIndex)) {
				console.log('zudang' + getChessValue(midIndex))
				return false;
			}
			// 落点位置是同色棋子
			if (getChessColor(index) == getCheckChessColor()) {
				
				console.log('tongse')
				return false;
			}
			// 不能过河
			let bigY = beforeY > afterY ? beforeY : afterY;
			let smallY = beforeY > afterY ? afterY : beforeY;
			if (smallY < midLine && midLine < bigY) {
				console.log('guoge')
				return false;
			}
			return true;
		}
		console.log('tian')
		return false;
	}

	/**
	 * 士
	 * @param {落点索引} index 
	 */
	function shi(index) {
		let midX = Math.floor(xCoordLength / 2)
		// 移动错误
		if (!(Math.abs(beforeX - afterX) == 1 && Math.abs(beforeY - afterY) == 1)) {
			return false;
		}
		// 超过x轴移动区域
		if (Math.abs(afterX - midX) > 1) {
			return false;
		}
		// 超过y轴移动区域
		if (afterY > 2 && afterY < 7) {
			return false;
		}
		// 落点位置是同色棋子
		if (getChessColor(index) == getCheckChessColor()) {
			return false;
		}
		return true;
	}

	/**
	 * 将
	 * @param {落点索引} index 
	 */
	function jiang(index) {
		let midX = Math.floor(xCoordLength / 2)
		// 移动错误
		if (!((beforeX == afterX && Math.abs(beforeY - afterY) == 1) || (beforeY == afterY && Math.abs(beforeX - afterX) == 1))) {
			return false;
		}
		// 超过x轴移动区域
		if (Math.abs(afterX - midX) > 1) {
			return false;
		}
		// 超过y轴移动区域
		if (afterY > 2 && afterY < 7) {
			return false;
		}
		// 落点位置是同色棋子
		if (getChessColor(index) == getCheckChessColor()) {
			return false;
		}
		return true;

	}

	/**
	 * 炮
	 * @param {落点索引} index 
	 */
	function pao(index) {
		// 不是直走
		if (beforeX != afterX && beforeY != afterY) {
			return false;
		}
		// 行走路径的索引
		let bigIndex = index > checkChessIndex ? index : checkChessIndex;
		let smallIndex = index > checkChessIndex ? checkChessIndex : index;
		let nextChessSpan = 0;
		// 延x直走,y不变
		if (beforeY == afterY) {
			// 下一个棋子距当前棋子的位移
			nextChessSpan = 1;
		}
		// 延y直走,x不变
		if (beforeX == afterX) {
			// 下一个棋子距当前棋子的位移
			nextChessSpan = xCoordLength;
		}
		let midChessNumber = 0;
		// 查看中间棋子数量
		for (let i = smallIndex + nextChessSpan; i < bigIndex; i += nextChessSpan) {
			if (getChessValue(i)) {
				midChessNumber++;
			}
		}
		if ((midChessNumber == 0 && !getChessValue(index)) || (midChessNumber == 1 && getChessColor(index) != getCheckChessColor())) {
			return true;
		}
		return false;
	}

	function bing(index) {
		let offset = goY[getCheckChessColor()];
		// 前进
		if ((beforeY + offset == afterY) && beforeX == afterX) {
			return true;
		}
		// 未过河
		if ((offset == 1 && afterY <= 4) || (offset == -1 && afterY > 4)) {
			return false;
		}
		// 过河，左右
		if (beforeY == afterY && Math.abs(beforeX - afterX) == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 游戏结束的提示信息
	 */
	function gameoverTip() {
		alert(gameovermsg);
	}

	/**
	 * 获取坐标
	 */
	function getCoord(index) {
		let coord = {
			x: 0,
			y: 0
		}
		coord.x = index % xCoordLength;
		coord.y = Math.floor(index / xCoordLength);
		return coord;
	}

	/**
	 * 获取坐标对应的数组索引
	 */
	function getIndex(x, y) {
		console.log(x,y);
		return y * xCoordLength + x;
	}

	/**
	 * 获取棋子颜色
	 * @param {board索引} index 
	 */
	function getChessColor(index) {

		for (let key in board[index]) {
			return key;
		}
		return null;
	}

	/**
	 * 获取棋子值
	 * @param {board索引} index 
	 */
	function getChessValue(index) {
		for (let key in board[index]) {
			return board[index][key];
		}
		return null;
	}

	/**
	 * 查看当前索引是否有棋子
	 * @param {board索引} index 
	 */
	function isHasChess(index) {
		for (let key in board[index]) {
			return true;
		}
		return false;
	}

	/**
	 * 查看当前索引是否是友方
	 * @param {board索引} index 
	 */
	function isFriendChess(index) {
		// 落点棋子颜色
		let dropPointColor = getChessColor(index);
		if (getExeChessColor() == dropPointColor) {
			return true;
		} else {
			return false;
		}
	}

	// 获取当前执行棋子的颜色
	function getExeChessColor() {
		return exeColor[exeIndex];
	}

	// 获取选中棋子的颜色
	function getCheckChessColor() {
		for (let key in board[checkChessIndex]) {
			return key;
		}
		return null;
	}

	// 获取选中棋子的值
	function getCheckChessValue() {
		for (let key in board[checkChessIndex]) {
			return board[checkChessIndex][key];
		}
		return null;
	}

	function copy(json) {
		return JSON.parse(JSON.stringify(json));
	}
});


