// Double Ended Queue

public class LinkedListDeque<T> implements Deque<T> {
	// 内部类
	public class Node {
		private T item;
		private Node next;
		private Node prev;

		public Node(T item, Node next, Node prev) {
			this.item = item;
			this.next = next;
			this.prev = prev;
		}
	}

	// 属性
	private Node head;
	private Node tail;
	private int size;


	// 方法	

	public LinkedListDeque() {
		head = new Node(null, null, null);
		tail = new Node(null, null, null);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	public void addFirst(T item) {
		if (size == 0) {
			Node p = new Node(item, tail, head);
			tail.prev = p;
			head.next = p;
		} else {
			Node p = new Node(item, head.next, head);
			head.next   = p;
			p.next.prev = p;
		}
		size += 1;
	}

	public void addLast(T item) {
		if (size == 0) {
			addFirst(item);
		} else {
			Node p = new Node(item, tail, tail.prev);
			tail.prev = p;
			p.prev.next = p;
			size += 1;
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}	

	public int size() {
		return size;
	}

	public void printDeque() {
		Node temp = head.next;
		String res = "";
		for (int i = 0; i < size; i++) {
			res = temp.item.toString() + " ";
			temp = temp.next;
		}
		System.out.print(res);
	}

	public T removeFirst() {
		Node temp = head.next;
		if (isEmpty()) {
			return null;
		}
		if (size == 1) {
			head.next = tail;
			tail.prev = head;
		} else {
			head.next = head.next.next;
			head.next.prev = head;
		}
		size -= 1;
		return temp.item;
	}

	public T removeLast() {
		Node temp;
		if (isEmpty()) {
			return null;
		}
		if (size == 1) {
			temp = tail.prev;
			tail.prev = head;
			head.next = tail;
		} else {
			temp = tail.prev;
			tail.prev = tail.prev.prev;
			tail.prev.next = tail;
		}
		size -= 1;
		return temp.item;
	}

	public T get(int index) {
		if (index < 0 || index > size) {
			return null;
		}
		// 从头开始
		if (index <= size / 2) {
			Node temp = head.next;
			for (int i = 0; i < index; i++) {
				temp = temp.next;
			}
			return temp.item;
		} else {
			Node temp = tail.prev;
			for (int i = size - 1; i > index; i--) {
				temp = temp.prev;
			}
			return temp.item;
		}
	}

	public T getRecursive(int index) {
		if (index >= size) {
			return null;
		} else {
			return recur(head.next, index);
		}
	}

	private T recur(Node n, int i) {
		if (i == 0) {
			return n.item;
		} else {
			return recur(n.next, i - 1);
		}
	}

} 	

