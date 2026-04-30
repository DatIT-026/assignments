// ============================================================
//  data/products.js  —  BƯỚC 1: Dữ liệu & Array Methods
// ============================================================
//
//  Kiến thức luyện tập:
//    - Array literals & Object literals
//    - Array.filter()
//    - Array.sort()
//    - Array.find()
//    - Array.map()
//    - RegExp + .test()
//
// ============================================================


// ── 1.1  Dữ liệu gốc ────────────────────────────────────────
//  Tạo mảng products với ít nhất 8 sản phẩm.
//  Mỗi sản phẩm có: id, name, price, category, stock (số lượng kho)
//  Danh mục hợp lệ: "electronics" | "clothing" | "food"
//  TODO: Điền dữ liệu vào mảng bên dưới 👇

export const products = [
  { id: 1, title: "Lenovo LOQ 2024", price: 21200000, category: "electronics", stock: 5},
  { id: 2, title: "Highlands Coffee", price: 150000, category: "food", stock: 25},
  { id: 3, title: "Pizza Hut", price: 450000, category: "food", stock: 16},
  { id: 4, title: "Asus Tuf Gaming", price: 17250000, category: "electronics", stock: 9},
  { id: 5, title: "Iphone 17 Pro Max", price: 29500000, category: "electronics", stock: 17},
  { id: 6, title: "Addiddy", price: 250000, category: "clothing", stock: 246},
  { id: 7, name: "Marvelous Clothes Full Combo", price: 1550000, category: "clothing", stock: 21},
  { id: 8, name: "Hot dogs", price: 25000, category: "food", stock: 643},
];


// ── 1.2  filterByCategory ───────────────────────────────────
export function filterByCategory(list, category) {
  if (category === "all") return list;
  return list.filter(p => p.category === category);
}


// ── 1.3  sortByPrice ────────────────────────────────────────
//  Nhận vào order: "asc" (tăng dần) hoặc "desc" (giảm dần).
//  Dùng .slice() để tránh thay đổi mảng gốc, rồi dùng .sort().
//
//  ⚠️  Lưu ý: .sort() mặc định sắp xếp theo chuỗi, không phải số!
//      Cần dùng comparator: (a, b) => a.price - b.price
//
//  @param {Array}  list   - mảng sản phẩm cần sắp xếp
//  @param {string} order  - "asc" | "desc"
//  @returns {Array}
//
//  TODO: Viết hàm bên dưới 👇

export function sortByPrice(list, order) {
  if (order === "asc") return list.slice().sort((a, b) => a.price - b.price) ;
  else if (order === "desc") return list.slice().sort((a, b) => b.price - a.price);
  else return list;
}


// ── 1.4  searchProducts ─────────────────────────────────────
//  Tìm sản phẩm theo tên, KHÔNG phân biệt hoa/thường.
//  Dùng: new RegExp(keyword, 'i')  rồi regex.test(product.name)
//
//  Nếu keyword rỗng ("") → trả về toàn bộ list.
//
//  @param {Array}  list    - mảng sản phẩm
//  @param {string} keyword - từ khoá tìm kiếm
//  @returns {Array}
//
//  TODO: Viết hàm bên dưới 👇

export function searchProducts(list, keyword) {
  if (keyword === "") return list;
  const myReg = new RegExp(keyword, "i");
  return list.filter(p => myReg.test(p.title ?? p.name));
}



// ── 1.5  getProductById ─────────────────────────────────────
//  Tìm và trả về 1 sản phẩm duy nhất theo id.
//  Dùng .find()
//
//  @param {number} id
//  @returns {Object | undefined}
//
//  TODO: Viết hàm bên dưới 👇

export function getProductById(id) {
  return products.find(p => p.id === id);
}

// ── 1.6  formatProducts ─────────────────────────────────────
//  Dùng .map() để tạo mảng mới, mỗi phần tử chỉ gồm:
//  { id, name, formattedPrice }
//  Trong đó formattedPrice là chuỗi dạng: "890,000 VND"
//  Gợi ý: price.toLocaleString('vi-VN') + ' VND'
//
//  @param {Array} list
//  @returns {Array}
//
//  TODO: Viết hàm bên dưới 👇

export function formatProducts(list) {
  return list.map(p => (
    {
      id: p.id,
      name: p.name,
      formattedPrice: p.price.toLocaleString('vi-VN') + ' VND'
    }));
}