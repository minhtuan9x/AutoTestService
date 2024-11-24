import { chromium, Page } from 'playwright';

let page: Page;

beforeAll(async () => {
    const browser = await chromium.launch();
    page = await browser.newPage();
});

afterAll(async () => {
    await page.close();
});

test('should have a button with text "Tìm trên Google"', async () => {
    await page.goto('https://google.com');
    expect(await page.locator('input[type="submit"]')).toHaveText('Tìm trên Google');
});