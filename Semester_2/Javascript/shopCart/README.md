# ShopCart.js — Bài tập JavaScript thực chiến

## Cấu trúc project

```
shopCart/
├── index.html                  ← Giao diện (không cần sửa)
├── style.css                   ← CSS (không cần sửa)
├── main.js                     ← Điều phối app — hoàn thành sau cùng
│
├── data/
│   └── products.js             ← BƯỚC 1: Array Methods & RegExp
│
├── cart/
│   └── cartManager.js          ← BƯỚC 2: Logic giỏ hàng & Closure
│
├── validation/
│   └── validate.js             ← BƯỚC 3: Validate với RegExp
│
├── api/
│   └── productApi.js           ← BƯỚC 4: Fetch API & Async/Await
│
└── ui/
    └── renderer.js             ← BƯỚC 5: DOM & localStorage
```

---

## Thứ tự làm bài

Làm theo đúng thứ tự — mỗi bước dựa trên bước trước:

```
Bước 1 → Bước 2 → Bước 3 → Bước 4 → Bước 5 → main.js
```

---

## Cách chạy project

Vì dùng ES Modules (`import/export`), bạn cần chạy qua server,
không mở file HTML trực tiếp được.

**Cách 1 — VS Code (khuyên dùng):**
- Cài extension "Live Server"
- Chuột phải vào `index.html` → "Open with Live Server"

**Cách 2 — Terminal:**
```bash
# Nếu có Python
python3 -m http.server 3000

# Nếu có Node.js
npx serve .
```

---

## Checklist tự kiểm tra

### Bước 1 — data/products.js
- [ ] Mảng `products` có ít nhất 8 sản phẩm
- [ ] `filterByCategory("electronics")` trả về đúng sản phẩm
- [ ] `filterByCategory("all")` trả về tất cả
- [ ] `sortByPrice(list, "asc")` giá tăng dần (không làm thay đổi mảng gốc)
- [ ] `sortByPrice(list, "desc")` giá giảm dần
- [ ] `searchProducts(list, "tai nghe")` tìm được, không phân biệt hoa/thường
- [ ] `searchProducts(list, "")` trả về toàn bộ
- [ ] `getProductById(1)` trả về đúng sản phẩm
- [ ] `formatProducts(list)` trả về mảng có `formattedPrice` dạng "890,000 VND"

### Bước 2 — cart/cartManager.js
- [ ] `addToCart(product)` thêm sản phẩm mới vào cart
- [ ] `addToCart(product)` tăng qty nếu đã có
- [ ] `removeFromCart(id)` xoá khỏi cart, không dùng splice
- [ ] `updateQty(id, 0)` hoặc số âm → tự xoá sản phẩm
- [ ] `getTotal()` tính đúng tổng tiền bằng reduce
- [ ] `createDiscount(10)(500000)` → trả về 450000
- [ ] `createDiscount(20)(500000)` → trả về 400000
- [ ] `getCart()` trả về bản sao (thay đổi kết quả không ảnh hưởng cart gốc)

### Bước 3 — validation/validate.js
- [ ] `validateEmail("user@gmail.com")` → valid: true
- [ ] `validateEmail("usergmail.com")`  → valid: false
- [ ] `validateEmail("")`              → valid: false
- [ ] `validatePhone("0912345678")`    → valid: true
- [ ] `validatePhone("0212345678")`    → valid: false (đầu số sai)
- [ ] `validatePhone("091234567")`     → valid: false (thiếu 1 số)
- [ ] `validateCoupon("VN2024")`       → valid: true
- [ ] `validateCoupon("vn2024")`       → valid: false (chữ thường)
- [ ] `validateCoupon("VN123")`        → valid: false (thiếu 1 số)
- [ ] `validateAll({...})` trả về đúng `isValid` và `errors`

### Bước 4 — api/productApi.js
- [ ] `fetchProducts()` trả về mảng sản phẩm từ fakestoreapi.com
- [ ] `fetchCategories()` trả về mảng categories
- [ ] `fetchProductById(1)` trả về 1 sản phẩm
- [ ] `fetchProductsAndCategories()` gọi 2 endpoint đồng thời (Promise.all)
- [ ] `fetchWithLoading(fn)` hiện loading state khi đang fetch
- [ ] Khi mạng lỗi → không crash, trả về null hoặc []

### Bước 5 — ui/renderer.js
- [ ] Sản phẩm hiển thị đầy đủ trên màn hình
- [ ] Nhấn "Thêm vào giỏ" → cart cập nhật
- [ ] Nhấn "+/-" trong cart → số lượng thay đổi
- [ ] Khi qty về 0 → sản phẩm tự bị xoá khỏi cart
- [ ] Nhấn "Xoá" → sản phẩm bị xoá
- [ ] Tổng tiền cập nhật đúng
- [ ] Refresh trang → giỏ hàng vẫn còn (localStorage)
- [ ] BONUS: Animation khi thêm vào giỏ

### main.js (tổng hợp)
- [ ] Tìm kiếm, lọc, sắp xếp hoạt động realtime
- [ ] Form checkout validate đúng, hiện lỗi rõ ràng
- [ ] Mã giảm giá áp dụng được, tính đúng tổng sau giảm
- [ ] Đặt hàng thành công → giỏ hàng được xoá

---

## Gợi ý debug

```javascript
// Dán vào Console (F12) để test nhanh từng hàm
import('./data/products.js').then(m => {
  console.log(m.filterByCategory('electronics'));
  console.log(m.searchProducts(m.products, 'tai'));
});
```

---

## Kiến thức đã cover

| Kiến thức          | File                    |
|--------------------|-------------------------|
| Array methods      | data/products.js        |
| RegExp             | data/products.js + validation/validate.js |
| Arrow functions    | Xuyên suốt tất cả file  |
| Closure            | cart/cartManager.js     |
| Spread operator    | cart/cartManager.js     |
| Destructuring      | validation/validate.js  |
| async/await        | api/productApi.js       |
| fetch API          | api/productApi.js       |
| Promise.all        | api/productApi.js       |
| try/catch          | api/productApi.js       |
| localStorage       | ui/renderer.js          |
| DOM manipulation   | ui/renderer.js          |
| Template literals  | ui/renderer.js          |
| Event delegation   | ui/renderer.js          |
| ES Modules         | Tất cả file             |
